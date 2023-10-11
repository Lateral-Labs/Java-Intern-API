package com.example.partnerapi.service;

import com.example.partnerapi.dto.acceptLeaseOfferDTO.AcceptLeaseOfferRequestDTO;
import com.example.partnerapi.dto.acceptLeaseOfferDTO.AcceptLeaseOfferOrApplicationStatusResponseDTO;
import com.example.partnerapi.dto.applicationStatusDTO.ApplicationStatusResponseDTO;
import com.example.partnerapi.dto.completeWorkDTO.CompleteWorkResponseDTO;
import com.example.partnerapi.dto.completeWorkDTO.CompleteWorkRequestDTO;
import com.example.partnerapi.dto.completeWorkDTO.SelectedBrandDTO;
import com.example.partnerapi.dto.completeWorkDTO.WorkCompletionDocDTO;
import com.example.partnerapi.dto.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.dto.submitPartnerApplicationDTO.*;
import com.example.partnerapi.model.*;
import com.example.partnerapi.repository.ApplicationRepository;
import com.example.partnerapi.repository.LeaseOptionRepository;
import com.example.partnerapi.util.ApplicationCategoryEnum;
import com.example.partnerapi.util.StatusApplicationEnum;
import com.example.partnerapi.util.SystemTypeCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PartnerServiceImpl implements PartnerService {

    private ApplicationRepository applicationRepository;
    private LeaseOptionRepository leaseOptionRepository;

    @Value("${secretKey}")
    String secretKey;

    @Value("${dealerKey}")
    String dealerKey;

    @Value("${basicUrl}")
    String basicUrl;


    @Autowired
    public PartnerServiceImpl(ApplicationRepository applicationRepository, LeaseOptionRepository leaseOptionRepository) {
        this.applicationRepository = applicationRepository;
        this.leaseOptionRepository = leaseOptionRepository;
    }

    @Override
    public Long getDataAndSaveANewApplication(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO) {
        Application newApplication = new Application(dataForANewApplicationRequestDTO);
        applicationRepository.save(newApplication);
        return newApplication.getId();
    }

    @Override
    public SubmitPartnerApplicationResponseDTO submitPartnerApplication(Long applicationId) throws IOException {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found."));
        String url = basicUrl + "partner/submit_app";
        DataForSubmitPartnerApplicationCallDTO dataForSubmitPartnerApplicationCallDTO = createDataForSubmitPartnerApplicationCall(application);
        validationForSubmitPartnerApplication(dataForSubmitPartnerApplicationCallDTO, application);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(dataForSubmitPartnerApplicationCallDTO);
        } catch (IOException e) {
            log.error("The mapping from object to json was not done.");
        }
        String responseBody = createPostRequestAndGetResponseBody(url, jsonStr);
        log.info("Response " + responseBody);
        SubmitPartnerApplicationResponseDTO submitPartnerApplicationResponseDTO = objectMapper.readValue(responseBody, SubmitPartnerApplicationResponseDTO.class);
        updateApplicationAfterSubmitPartnerApplication(application, submitPartnerApplicationResponseDTO);
        return submitPartnerApplicationResponseDTO;
    }

    @Override
    public void acceptLeaseOffer(AcceptLeaseOfferRequestDTO acceptLeaseOfferRequestDTO) throws IOException {
        Application application = applicationRepository.findById(acceptLeaseOfferRequestDTO.getApplicationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found."));
        validationForAcceptLeaseOffer(acceptLeaseOfferRequestDTO, application);
        String url = basicUrl + "accept_lease_offer";
        acceptLeaseOfferRequestDTO.setApplicationId(application.getPartnerAppId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(acceptLeaseOfferRequestDTO);
        } catch (IOException e) {
            log.error("Mapping DTO object to Json failed.");
        }
        String responseBody = createPostRequestAndGetResponseBody(url, jsonStr);
        log.info("Response " + responseBody);
        Integer acceptedTerm;
        AcceptLeaseOfferOrApplicationStatusResponseDTO acceptLeaseOfferOrApplicationStatusResponseDTO = objectMapper.readValue(responseBody, AcceptLeaseOfferOrApplicationStatusResponseDTO.class);
        if (acceptLeaseOfferRequestDTO.getAcceptedTerm() < 10) {
            acceptedTerm = acceptLeaseOfferRequestDTO.getAcceptedTerm() * 12;
        } else {
            acceptedTerm = acceptLeaseOfferRequestDTO.getAcceptedTerm();
        }
        LeaseOption leaseOption = leaseOptionRepository.findLeaseOptionByMonthsAndApplication(acceptedTerm, application).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The lease option was not found."));
        updateApplicationAfterAcceptLeaseOffer(application, acceptLeaseOfferOrApplicationStatusResponseDTO, leaseOption);
    }

    @Override
    public ApplicationStatusResponseDTO applicationStatus(Long id) throws JsonProcessingException {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found."));
        if (application.getPartnerAppId() != null) {
            getAndUpdateApplicationStatus(application);
        }
        return new ApplicationStatusResponseDTO(application);
    }

    @Override
    public CompleteWorkResponseDTO completeWork(CompleteWorkRequestDTO completeWorkRequestDTO) throws JsonProcessingException {
        Application application = applicationRepository.findById(completeWorkRequestDTO.getApplicationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found."));
        getAndUpdateApplicationStatus(application);
        validationForCompleteWork(completeWorkRequestDTO, application);
        String url = basicUrl + "partner_complete_work";
        completeWorkRequestDTO.setApplicationId(application.getPartnerAppId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(completeWorkRequestDTO);
        } catch (IOException e) {
            log.error("Mapping DTO object to Json failed.");
        }
        String responseBody = createPostRequestAndGetResponseBody(url, jsonStr);
        log.info("Response " + responseBody);
        CompleteWorkResponseDTO completeWorkResponseDTO = objectMapper.readValue(responseBody, CompleteWorkResponseDTO.class);
        if (completeWorkResponseDTO.getRtoNumber() != null) {
            updateApplicationCompleteWork(application, completeWorkRequestDTO);
            getAndUpdateApplicationStatus(application);
        }
        return completeWorkResponseDTO;
    }

    private void validationForSubmitPartnerApplication(DataForSubmitPartnerApplicationCallDTO dataForSubmitPartnerApplicationCallDTO, Application application) {
        if (!application.getStatus().equals(StatusApplicationEnum.NEW)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This application was already submitted.");
        } else if (dataForSubmitPartnerApplicationCallDTO.getApplicationData().getSystemType() == null && dataForSubmitPartnerApplicationCallDTO.getApplicationData().getWaterHeaterSystemType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must choose at least one of SystemType or WaterHeaterSystemType.");
        } else if ((dataForSubmitPartnerApplicationCallDTO.getApplicationData().getSystemType() != null && dataForSubmitPartnerApplicationCallDTO.getApplicationData().getTonnage() == null) || (dataForSubmitPartnerApplicationCallDTO.getApplicationData().getSecondSystemType() != null && dataForSubmitPartnerApplicationCallDTO.getApplicationData().getSecondSystemTonnage() == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must choose the tonnage for SystemType selected.");
        }
    }

    private void validationForAcceptLeaseOffer(AcceptLeaseOfferRequestDTO acceptLeaseOfferRequestDTO, Application application) {
        if (!(application.getStatus().getStatusDescription().equals("Fully Approved") || application.getStatus().getStatusDescription().equals("Partially Approved"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't accept lease offer because your application has a status incompatibility with this action.");
        } else if (!(acceptLeaseOfferRequestDTO.getAcceptedTerm() < 10 || acceptLeaseOfferRequestDTO.getAcceptedTerm() % 12 == 0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can accept the lease offer for a term of years or months.");
        }
    }

    private void validationForCompleteWork(CompleteWorkRequestDTO completeWorkRequestDTO, Application application) {
        int nrHVAC = 0;
        int nrWaterHeater = 0;
        for (SelectedBrandDTO selectedBrandDTO : completeWorkRequestDTO.getSelectedBrands()) {
            if (selectedBrandDTO.getSystemTypeCategory().equals("HVAC")) {
                nrHVAC++;
            } else if (selectedBrandDTO.getSystemTypeCategory().equals("Water Heater")) {
                nrWaterHeater++;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't access completeWork because systemTypeCategory of selectedBrand isn't correct.");
            }
        }
        int nrHVACApplication = 0;
        int nrWaterHeaterApplication = 0;
        for (HeaterSystem heaterSystem : application.getHeaterSystemList()) {
            if (heaterSystem.getSystemTypeCategory().equals(SystemTypeCategory.HVAC)) {
                nrHVACApplication++;
            } else {
                nrWaterHeaterApplication++;
            }
        }
        LocalDate installDate = LocalDate.parse(completeWorkRequestDTO.getInstallDate());
        if (!application.getSelectedBrandList().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The application has already been completed.");
        } else if (!(application.getStatus().getApplicationCategoryEnum().equals(ApplicationCategoryEnum.OPEN))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't access completeWork because your application status isn't Pre-approved/Decision Pending/Closed Won.");
        } else if (application.getIsAgreementSigned().equals(false)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't access completeWork because the agreement wasn't signed.");
        } else if (application.getFirstPayment() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't access completeWork because the first payment wasn't made.");
        } else if ((nrHVAC == 0 && nrHVACApplication > 0) || (nrHVAC > 0 && nrHVACApplication == 0) || (nrWaterHeater == 0 && nrWaterHeaterApplication > 0) || (nrWaterHeater > 0 && nrWaterHeaterApplication == 0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't access completeWork because one kind of HeaterSystem was not included in Submit Application and exists in SelectedBrandList from Complete Work, or vice versa.");
        } else if (installDate.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't access completeWork because the installDate is a previous date.");
        }
    }

    private DataForSubmitPartnerApplicationCallDTO createDataForSubmitPartnerApplicationCall(Application application) {
        ApplicationDataCallDTO applicationDataCallDTO = new ApplicationDataCallDTO(application);
        ApplicantCallDTO primaryApplicantCallDTO = null;
        ApplicantCallDTO coApplicantCallDTO = null;
        for (Applicant applicant : application.getApplicantList()) {
            if (applicant.getIsPrimary()) {
                primaryApplicantCallDTO = new ApplicantCallDTO(applicant);
            } else {
                coApplicantCallDTO = new ApplicantCallDTO(applicant);
            }
        }
        if (application.getApplicantList().size() == 1) {
            return new DataForSubmitPartnerApplicationCallDTO(applicationDataCallDTO, primaryApplicantCallDTO);
        }
        return new DataForSubmitPartnerApplicationCallDTO(applicationDataCallDTO, primaryApplicantCallDTO, coApplicantCallDTO);
    }

    private String createPostRequestAndGetResponseBody(String url, String jsonStr) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?secretKey=" + secretKey + "&dealerKey=" + dealerKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .build();
        log.info("URL: " + url + "?secretKey=" + secretKey + "&dealerKey=" + dealerKey);
        log.info("Method: POST");
        log.info("Json " + jsonStr);
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error("response error");
        }
        return response.body();
    }

    private String createGetRequestAndGetResponseBody(String url, String applicationIdStr) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?secretKey=" + secretKey + "&dealerKey=" + dealerKey + "&applicationId=" + applicationIdStr))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        log.info("URL: " + url + "?secretKey=" + secretKey + "&dealerKey=" + dealerKey + "&applicationId=" + applicationIdStr);
        log.info("Method: GET");
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error("response error");
        }
        return response.body();
    }


    private void updateApplicationAfterSubmitPartnerApplication(Application application, SubmitPartnerApplicationResponseDTO submitPartnerApplicationResponseDTO) {
        application.setPartnerAppId(submitPartnerApplicationResponseDTO.getApplicationId());
        application.setStatus(StatusApplicationEnum.valueOf(submitPartnerApplicationResponseDTO.getStatus(), submitPartnerApplicationResponseDTO.getStatusDescription()));
        application.setReasons(submitPartnerApplicationResponseDTO.getReasons());
        application.setRequestedLeaseAmount(submitPartnerApplicationResponseDTO.getRequestedLeaseAmount());
        application.setPreApprovedLeaseAmount(submitPartnerApplicationResponseDTO.getPreApprovedLeaseAmount());
        application.setDownPayment(submitPartnerApplicationResponseDTO.getDownPayment());
        application.setRetailCashPrice(submitPartnerApplicationResponseDTO.getRetailCashPrice());
        application.setToken(submitPartnerApplicationResponseDTO.getToken());
        if (submitPartnerApplicationResponseDTO.getLeaseOptions() != null) {
            application.setLeaseOptionList(mapLeaseOption(submitPartnerApplicationResponseDTO.getLeaseOptions(), application));
        }
        applicationRepository.save(application);
    }

    private void updateApplicationAfterAcceptLeaseOffer(Application application, AcceptLeaseOfferOrApplicationStatusResponseDTO acceptLeaseOfferOrApplicationStatusResponseDTO, LeaseOption leaseOption) {
        application.setStatus(StatusApplicationEnum.valueOf(acceptLeaseOfferOrApplicationStatusResponseDTO.getProcessingStatus(), acceptLeaseOfferOrApplicationStatusResponseDTO.getProcessingStatusDescription()));
        application.setApplicationStatus(acceptLeaseOfferOrApplicationStatusResponseDTO.getApplicationStatus());
        application.setRtoNumber(acceptLeaseOfferOrApplicationStatusResponseDTO.getRtoNumber());
        application.setInsertedTime(acceptLeaseOfferOrApplicationStatusResponseDTO.getInsertedTime());
        application.setFirstPayment(acceptLeaseOfferOrApplicationStatusResponseDTO.getFirstPayment());
        application.setIsAgreementSigned(acceptLeaseOfferOrApplicationStatusResponseDTO.getIsAgreementSigned());
        application.setRetailCashPrice(acceptLeaseOfferOrApplicationStatusResponseDTO.getRetailCashPrice());
        leaseOption.setIsSelected(true);
        applicationRepository.save(application);
    }

    private void getAndUpdateApplicationStatus(Application application) throws JsonProcessingException {
        String url = basicUrl + "app_status";
        String applicationIdStr = Long.toString(application.getPartnerAppId());
        String responseBody = createGetRequestAndGetResponseBody(url, applicationIdStr);
        log.info("Response " + responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        ArrayNode applicationsStatusJson = (ArrayNode) root;
        for (JsonNode applicationStatusJson : applicationsStatusJson) {
            AcceptLeaseOfferOrApplicationStatusResponseDTO acceptLeaseOfferOrApplicationStatusResponseDTO = objectMapper.convertValue(applicationStatusJson, AcceptLeaseOfferOrApplicationStatusResponseDTO.class);
            updateApplicationStatus(acceptLeaseOfferOrApplicationStatusResponseDTO);
        }
    }

    private void updateApplicationStatus(AcceptLeaseOfferOrApplicationStatusResponseDTO acceptLeaseOfferOrApplicationStatusResponseDTO) {
        Application application = applicationRepository.findByPartnerAppId(acceptLeaseOfferOrApplicationStatusResponseDTO.getApplicationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        application.setApplicationStatus(acceptLeaseOfferOrApplicationStatusResponseDTO.getApplicationStatus());
        application.setStatus(StatusApplicationEnum.valueOf(acceptLeaseOfferOrApplicationStatusResponseDTO.getProcessingStatus(), acceptLeaseOfferOrApplicationStatusResponseDTO.getProcessingStatusDescription()));
        application.setIsAgreementSigned(acceptLeaseOfferOrApplicationStatusResponseDTO.getIsAgreementSigned());
        application.setFirstPayment(acceptLeaseOfferOrApplicationStatusResponseDTO.getFirstPayment());
        application.setTotalJobPrice(acceptLeaseOfferOrApplicationStatusResponseDTO.getTotalJobPrice());
        application.setDownPayment(acceptLeaseOfferOrApplicationStatusResponseDTO.getDownPaymentAmount());
        application.setRetailCashPrice(acceptLeaseOfferOrApplicationStatusResponseDTO.getRetailCashPrice());
        LeaseOption leaseOption = leaseOptionRepository.findByIsSelectedAndApplication(true, application);
        if (leaseOption != null) {
            if (leaseOption.getMonths() != acceptLeaseOfferOrApplicationStatusResponseDTO.getLeaseOptions().get(0).getMonths()) {
                leaseOption.setIsSelected(false);
                LeaseOption newLeaseOption = leaseOptionRepository.findLeaseOptionByMonthsAndApplication(acceptLeaseOfferOrApplicationStatusResponseDTO.getLeaseOptions().get(0).getMonths(), application).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lease option was not found"));
                newLeaseOption.setIsSelected(true);
            }
        }
        applicationRepository.save(application);
    }

    private void updateApplicationCompleteWork(Application application, CompleteWorkRequestDTO completeWorkRequestDTO) {
        application.setInstallDate(completeWorkRequestDTO.getInstallDate());
        application.setWorkCompletionDocList(mapWorkCompletionDoc(completeWorkRequestDTO.getWorkCompletionDocs(), application));
        application.setSelectedBrandList(mapSelectedBrand(completeWorkRequestDTO.getSelectedBrands(), application));
    }

    private List<LeaseOption> mapLeaseOption(List<LeaseOptionDTO> leaseOptionDTOList, Application application) {
        List<LeaseOption> leaseOptionList = new ArrayList<>();
        for (LeaseOptionDTO leaseOptionDTO : leaseOptionDTOList) {
            leaseOptionList.add(new LeaseOption(leaseOptionDTO, application));
        }
        return leaseOptionList;
    }

    private List<SelectedBrand> mapSelectedBrand(List<SelectedBrandDTO> selectedBrands, Application application) {
        List<SelectedBrand> selectedBrandList = new ArrayList<>();
        for (SelectedBrandDTO selectedBrandDTO : selectedBrands) {
            selectedBrandList.add(new SelectedBrand(selectedBrandDTO, application));
        }
        return selectedBrandList;
    }

    private List<WorkCompletionDoc> mapWorkCompletionDoc(List<WorkCompletionDocDTO> workCompletionDocs, Application application) {
        List<WorkCompletionDoc> workCompletionDocList = new ArrayList<>();
        for (WorkCompletionDocDTO workCompletionDocDTO : workCompletionDocs) {
            workCompletionDocList.add(new WorkCompletionDoc(workCompletionDocDTO, application));
        }
        return workCompletionDocList;
    }

}
