package com.example.partnerapi.service;

import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferRequestDTO;
import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferResponseDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.AddressDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.ApplicantDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.SalesmanDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.*;
import com.example.partnerapi.model.*;
import com.example.partnerapi.model.HeaterSystem;
import com.example.partnerapi.repository.ApplicationRepository;
import com.example.partnerapi.repository.LeaseOptionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        String responseBody = createRequestAndGetResponseBody(url, jsonStr);
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

    public String createRequestAndGetResponseBody(String url, String jsonStr) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?secretKey=" + secretKey + "&dealerKey=" + dealerKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            log.error("response error");
        } catch (InterruptedException e) {
            log.error("response error");
        }
        return response.body();
    }

    @Override
    public void acceptLeaseOffer(AcceptLeaseOfferRequestDTO acceptLeaseOfferRequestDTO) throws IOException, InterruptedException {
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
        String responseBody = createRequestAndGetResponseBody(url, jsonStr);
        log.info("Response " + responseBody);
        AcceptLeaseOfferResponseDTO acceptLeaseOfferResponseDTO = objectMapper.readValue(responseBody, AcceptLeaseOfferResponseDTO.class);
        LeaseOption leaseOption = leaseOptionRepository.findLeaseOptionByMonthsAndApplication(acceptLeaseOfferRequestDTO.getAcceptedTerm(), application).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The lease option was not found"));
        updateApplicationAfterAcceptLeaseOffer(application, acceptLeaseOfferResponseDTO, leaseOption);
    }

    public void updateApplicationAfterAcceptLeaseOffer(Application application, AcceptLeaseOfferResponseDTO acceptLeaseOfferResponseDTO, LeaseOption leaseOption) {
        application.setStatus(acceptLeaseOfferResponseDTO.getProcessingStatus());
        application.setStatusDescription(acceptLeaseOfferResponseDTO.getProcessingStatusDescription());
        application.setApplicationStatus(acceptLeaseOfferResponseDTO.getApplicationStatus());
        application.setRtoNumber(acceptLeaseOfferResponseDTO.getRtoNumber());
        application.setInsertedTime(acceptLeaseOfferResponseDTO.getInsertedTime());
        application.setFirstPayment(acceptLeaseOfferResponseDTO.getFirstPayment());
        application.setIsAgreementSigned(acceptLeaseOfferResponseDTO.getIsAgreementSigned());
        leaseOption.setIsSelected(true);
        applicationRepository.save(application);
    }


}
