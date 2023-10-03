package com.example.partnerapi.service;

import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferRequestDTO;
import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferOrApplicationStatusResponseDTO;
import com.example.partnerapi.DTO.applicationStatusDTO.ApplicationStatusResponseDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.*;
import com.example.partnerapi.model.*;
import com.example.partnerapi.repository.ApplicationRepository;
import com.example.partnerapi.repository.LeaseOptionRepository;
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
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        String url = basicUrl + "v2/partner/submit_app";
        DataForSubmitPartnerApplicationCallDTO dataForSubmitPartnerApplicationCallDTO = createDataForSubmitPartnerApplicationCall(application);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(dataForSubmitPartnerApplicationCallDTO);
            java.lang.System.out.println(jsonStr);
        } catch (IOException e) {
            log.error("The mapping from object to json was not done");
        }
        String responseBody = createPostRequestAndGetResponseBody(url, jsonStr);
        log.info("Response " + responseBody);
        SubmitPartnerApplicationResponseDTO submitPartnerApplicationResponseDTO = objectMapper.readValue(responseBody, SubmitPartnerApplicationResponseDTO.class);
        updateApplication(application, submitPartnerApplicationResponseDTO);
        return submitPartnerApplicationResponseDTO;
    }

    private void updateApplication(Application application, SubmitPartnerApplicationResponseDTO submitPartnerApplicationResponseDTO) {
        application.setPartnerAppId(submitPartnerApplicationResponseDTO.getApplicationId());
        application.setStatus(submitPartnerApplicationResponseDTO.getStatus());
        application.setStatusDescription(submitPartnerApplicationResponseDTO.getStatusDescription());
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

    public List<LeaseOption> mapLeaseOption(List<LeaseOptionDTO> leaseOptionDTOList, Application application) {
        List<LeaseOption> leaseOptionList = new ArrayList<>();
        for (LeaseOptionDTO leaseOptionDTO : leaseOptionDTOList) {
            leaseOptionList.add(new LeaseOption(leaseOptionDTO, application));
        }
        return leaseOptionList;
    }

    public DataForSubmitPartnerApplicationCallDTO createDataForSubmitPartnerApplicationCall(Application application) {
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

    public String createPostRequestAndGetResponseBody(String url, String jsonStr) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?secretKey=" + secretKey + "&dealerKey=" + dealerKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error("response error");
        }
        return response.body();
    }

    @Override
    public void acceptLeaseOffer(AcceptLeaseOfferRequestDTO acceptLeaseOfferRequestDTO) throws IOException {
        String url = basicUrl + "v2/accept_lease_offer";
        Application application = applicationRepository.findById(acceptLeaseOfferRequestDTO.getApplicationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        acceptLeaseOfferRequestDTO.setApplicationId(application.getPartnerAppId());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(acceptLeaseOfferRequestDTO);
            log.info("Json " + jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseBody = createPostRequestAndGetResponseBody(url, jsonStr);
        log.info("Response " + responseBody);
        AcceptLeaseOfferOrApplicationStatusResponseDTO acceptLeaseOfferOrApplicationStatusResponseDTO = objectMapper.readValue(responseBody, AcceptLeaseOfferOrApplicationStatusResponseDTO.class);
        LeaseOption leaseOption = leaseOptionRepository.findLeaseOptionByMonthsAndApplication(acceptLeaseOfferRequestDTO.getAcceptedTerm(), application).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The lease option was not found"));
        updateApplicationAfterAcceptLeaseOffer(application, acceptLeaseOfferOrApplicationStatusResponseDTO, leaseOption);
    }

    public void updateApplicationAfterAcceptLeaseOffer(Application application, AcceptLeaseOfferOrApplicationStatusResponseDTO acceptLeaseOfferOrApplicationStatusResponseDTO, LeaseOption leaseOption) {
        application.setStatus(acceptLeaseOfferOrApplicationStatusResponseDTO.getProcessingStatus());
        application.setStatusDescription(acceptLeaseOfferOrApplicationStatusResponseDTO.getProcessingStatusDescription());
        application.setApplicationStatus(acceptLeaseOfferOrApplicationStatusResponseDTO.getApplicationStatus());
        application.setRtoNumber(acceptLeaseOfferOrApplicationStatusResponseDTO.getRtoNumber());
        application.setInsertedTime(acceptLeaseOfferOrApplicationStatusResponseDTO.getInsertedTime());
        application.setFirstPayment(acceptLeaseOfferOrApplicationStatusResponseDTO.getFirstPayment());
        application.setIsAgreementSigned(acceptLeaseOfferOrApplicationStatusResponseDTO.getIsAgreementSigned());
        application.setRetailCashPrice(acceptLeaseOfferOrApplicationStatusResponseDTO.getRetailCashPrice());
        leaseOption.setIsSelected(true);
        applicationRepository.save(application);
    }

    @Override
    public ApplicationStatusResponseDTO applicationStatus(Long id) throws JsonProcessingException {
        String url = basicUrl + "v2/app_status";
        Application application = applicationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
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
        return new ApplicationStatusResponseDTO(application);
    }

    public String createGetRequestAndGetResponseBody(String url, String applicationIdStr) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?secretKey=" + secretKey + "&dealerKey=" + dealerKey + "&applicationId=" + applicationIdStr))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error("response error");
        }
        return response.body();
    }

    public void updateApplicationStatus(AcceptLeaseOfferOrApplicationStatusResponseDTO acceptLeaseOfferOrApplicationStatusResponseDTO) {
        Application application = applicationRepository.findByPartnerAppId(acceptLeaseOfferOrApplicationStatusResponseDTO.getApplicationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        application.setApplicationStatus(acceptLeaseOfferOrApplicationStatusResponseDTO.getApplicationStatus());
        application.setStatus(acceptLeaseOfferOrApplicationStatusResponseDTO.getProcessingStatus());
        application.setStatusDescription(acceptLeaseOfferOrApplicationStatusResponseDTO.getProcessingStatusDescription());
        application.setIsAgreementSigned(acceptLeaseOfferOrApplicationStatusResponseDTO.getIsAgreementSigned());
        application.setFirstPayment(acceptLeaseOfferOrApplicationStatusResponseDTO.getFirstPayment());
        application.setTotalJobPrice(acceptLeaseOfferOrApplicationStatusResponseDTO.getTotalJobPrice());
        application.setDownPayment(acceptLeaseOfferOrApplicationStatusResponseDTO.getDownPaymentAmount());
        application.setRetailCashPrice(acceptLeaseOfferOrApplicationStatusResponseDTO.getRetailCashPrice());
        LeaseOption leaseOption = leaseOptionRepository.findByIsSelectedAndApplication(true, application).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lease option was not found"));
        if (leaseOption.getMonths() != (acceptLeaseOfferOrApplicationStatusResponseDTO.getLeaseOptions().get(0).getMonths())) {
            leaseOption.setIsSelected(false);
            LeaseOption newLeaseOption = leaseOptionRepository.findLeaseOptionByMonthsAndApplication(acceptLeaseOfferOrApplicationStatusResponseDTO.getLeaseOptions().get(0).getMonths(), application).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lease option was not found"));
            newLeaseOption.setIsSelected(true);
        }
        applicationRepository.save(application);
    }

}
