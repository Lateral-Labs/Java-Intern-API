package com.example.partnerapi.service;

import com.example.partnerapi.DTO.dataForANewApplicationDTO.AddressDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.ApplicantDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.*;
import com.example.partnerapi.model.*;
import com.example.partnerapi.model.System;
import com.example.partnerapi.repository.ApplicationRepository;
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

    @Value("${secretKey}")
    String secretKey;

    @Value("${dealerKey}")
    String dealerKey;

    @Value("${basicUrl}")
    String basicUrl;


    @Autowired
    public PartnerServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Long getDataAndSaveANewApplication(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO) {
        Application newApplication = new Application();
        newApplication.setSalesman(setDataForSalesman(dataForANewApplicationRequestDTO, newApplication));
        newApplication.setSystemList(setDataForSystems(dataForANewApplicationRequestDTO, newApplication));
        newApplication.setPropertyAddress(mapPropertyAddress(dataForANewApplicationRequestDTO.getPropertyAddress(), newApplication));//////
        newApplication.setTotalJobPrice(dataForANewApplicationRequestDTO.getTotalJobPrice());
        newApplication.setInsurance(dataForANewApplicationRequestDTO.getInsurance());
        newApplication.getApplicantList().add(mapApplicant(dataForANewApplicationRequestDTO.getPrimaryApplicant(), dataForANewApplicationRequestDTO.getHomeAddressPrimaryApplicant(), newApplication));
        newApplication.getApplicantList().add(mapApplicant(dataForANewApplicationRequestDTO.getCoApplicant(), dataForANewApplicationRequestDTO.getHomeAddressCoApplicant(), newApplication));
        applicationRepository.save(newApplication);
        return newApplication.getId();
    }

    private Applicant mapApplicant(ApplicantDTO applicantDTO, AddressDTO addressDTO, Application newApplication) {
        Applicant newApplicant = new Applicant();
        newApplicant.setNameFirst(applicantDTO.getNameFirst());
        newApplicant.setNameLast(applicantDTO.getNameLast());
        newApplicant.setPhoneMobile(applicantDTO.getPhoneMobile());
        newApplicant.setDateOfBirth(applicantDTO.getDateOfBirth());
        newApplicant.setSsn(applicantDTO.getSsn());
        newApplicant.setBankAccountingNumber(applicantDTO.getBankAccoutingNumber());
        newApplicant.setBankRoutingNumber(applicantDTO.getBankRoutingNumber());
        newApplicant.setEmail(applicantDTO.getEmail());
        newApplicant.setMonthlyIncome(applicantDTO.getMonthlyIncome());
        newApplicant.setIsPrimary(applicantDTO.getIsPrimary());
        newApplicant.setHomeAddress(mapHomeAddress(addressDTO, newApplicant));
        newApplicant.setApplication(newApplication);
        return newApplicant;
    }

    private Address mapHomeAddress(AddressDTO addressDTO, Applicant newApplicant) {
        Address newHomeAddress = mapAddressDtoToAddress(addressDTO);
        newHomeAddress.setApplicant(newApplicant);
        return newHomeAddress;
    }

    private Address mapPropertyAddress(AddressDTO addressDTO, Application newApplication) {
        Address newPropertyAddress = mapAddressDtoToAddress(addressDTO);
        newPropertyAddress.setApplicationPropertyAddress(newApplication);
        return newPropertyAddress;
    }

    private Address mapAddressDtoToAddress(AddressDTO addressDTO) {
        Address newAddress = new Address();
        newAddress.setCity(addressDTO.getCity());
        newAddress.setZipcode(addressDTO.getZipcode());
        newAddress.setStreet(addressDTO.getStreet());
        newAddress.setState(addressDTO.getState());
        return newAddress;
    }

    private static List<System> setDataForSystems(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO, Application newApplication) {
        List<System> systems = new ArrayList<>();
        for (int i = 0; i < dataForANewApplicationRequestDTO.getSystemList().size(); i++) {
            System newSystem = new System();
            newSystem.setSystemType(SystemType.valueOfSystemTypeName(dataForANewApplicationRequestDTO.getSystemList().get(i).getSystemType()));
            if (dataForANewApplicationRequestDTO.getSystemList().get(i).getSystemTypeCategory().equals("HVAC")) {
                newSystem.setSystemTypeCategory(SystemTypeCategory.HVAC);
                newSystem.setTonnage(dataForANewApplicationRequestDTO.getSystemList().get(i).getTonnage());
            } else if (dataForANewApplicationRequestDTO.getSystemList().get(i).getSystemTypeCategory().equals("Water Heater")) {
                newSystem.setSystemTypeCategory(SystemTypeCategory.WATER_HEATER);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "System Type Category isn't correct.");
            }
            newSystem.setIsSecondSystem(dataForANewApplicationRequestDTO.getSystemList().get(i).getIsSecondSystem());
            newSystem.setApplication(newApplication);
            systems.add(newSystem);
        }
        return systems;
    }

    private static Salesman setDataForSalesman(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO, Application newApplication) {
        Salesman newSalesman = new Salesman();
        newSalesman.setSalesmanName(dataForANewApplicationRequestDTO.getSalesman().getSalesmanName());
        newSalesman.setSalesmanEmail(dataForANewApplicationRequestDTO.getSalesman().getSalesmanEmail());
        newSalesman.setSalesmanPhone(dataForANewApplicationRequestDTO.getSalesman().getSalesmanPhone());
        newSalesman.setDealerName(dataForANewApplicationRequestDTO.getSalesman().getDealerName());
        newSalesman.setApplication(newApplication);
        return newSalesman;
    }

    @Override
    public SubmitPartnerApplicationResponseDTO submitPartnerApplication(Long applicationId) throws IOException, InterruptedException {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        String url = basicUrl + "v2/partner/submit_app";
        DataForSubmitPartnerApplicationCallDTO dataForSubmitPartnerApplicationCallDTO = createDataForSubmitPartnerApplicationCall(application);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(dataForSubmitPartnerApplicationCallDTO);
            java.lang.System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseBody = getResponseBodyFromSubmitPartnerApplication(url, jsonStr);
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
            application.setLeaseOptionList(mapLeaseOption(submitPartnerApplicationResponseDTO.getLeaseOptions()));
        }
        applicationRepository.save(application);
    }

    public List<LeaseOption> mapLeaseOption(List<LeaseOptionDTO> leaseOptionDTOList) {
        List<LeaseOption> leaseOptionList = new ArrayList<>();
        for (LeaseOptionDTO leaseOptionDTO : leaseOptionDTOList) {
            LeaseOption leaseOption = new LeaseOption();
            leaseOption.setMonths(leaseOptionDTO.getMonths());
            leaseOption.setPayment(leaseOptionDTO.getPayment());
            leaseOption.setIsSelected(leaseOptionDTO.getIsSelected());
            leaseOption.setCostOfLeaseServices(leaseOptionDTO.getCostOfLeaseServices());
            leaseOptionList.add(leaseOption);
        }
        return leaseOptionList;
    }

    public DataForSubmitPartnerApplicationCallDTO createDataForSubmitPartnerApplicationCall(Application application) {
        ApplicationDataCallDTO applicationDataCallDTO = getApplicationDataForSubmitPartnerApplicationCall(application);
        ApplicantCallDTO primaryApplicantCallDTO = null;
        ApplicantCallDTO coApplicantCallDTO = null;
        for (Applicant applicant : application.getApplicantList()) {
            if (applicant.getIsPrimary()) {
                primaryApplicantCallDTO = getApplicantForSubmitPartnerApplicantCall(applicant);
            } else {
                coApplicantCallDTO = getApplicantForSubmitPartnerApplicantCall(applicant);
            }
        }
        if (application.getApplicantList().size() == 1) {
            return new DataForSubmitPartnerApplicationCallDTO(applicationDataCallDTO, primaryApplicantCallDTO);
        }
        return new DataForSubmitPartnerApplicationCallDTO(applicationDataCallDTO, primaryApplicantCallDTO, coApplicantCallDTO);
    }

    private static ApplicationDataCallDTO getApplicationDataForSubmitPartnerApplicationCall(Application application) {
        ApplicationDataCallDTO applicationDataCallDTO = new ApplicationDataCallDTO();
        applicationDataCallDTO.setInsurance(application.getInsurance());
        applicationDataCallDTO.setTotalFinancingAmount(application.getTotalJobPrice()); //un baza de date nu am atributul TotalFinancingAmount deoarece acesta va fi egal cu TotalJobPrice
        applicationDataCallDTO.setPropertyStreet(application.getPropertyAddress().getStreet());
        applicationDataCallDTO.setPropertyZip(application.getPropertyAddress().getZipcode());
        applicationDataCallDTO.setPropertyState(application.getPropertyAddress().getState());
        applicationDataCallDTO.setTotalJobPrice(application.getTotalJobPrice());
        applicationDataCallDTO.setPropertyCity(application.getPropertyAddress().getCity());
        applicationDataCallDTO.setPropertyCounty(application.getPropertyAddress().getCountry());
        applicationDataCallDTO.setPartnerAppId(application.getId());
        applicationDataCallDTO.setDealerName(application.getSalesman().getDealerName());
        applicationDataCallDTO.setSalesmanName(application.getSalesman().getSalesmanName());
        applicationDataCallDTO.setSalesmanEmail(application.getSalesman().getSalesmanEmail());
        applicationDataCallDTO.setSalesmanPhone(application.getSalesman().getSalesmanPhone());
        mapSystemListToSystemListCallDTO(application, applicationDataCallDTO);
        return applicationDataCallDTO;
    }

    private static void mapSystemListToSystemListCallDTO(Application application, ApplicationDataCallDTO applicationDataCallDTO) {
        int i = 0;
        while (i < application.getSystemList().size()) {
            if (application.getSystemList().get(i).getSystemTypeCategory().equals(SystemTypeCategory.HVAC)) {
                if (application.getSystemList().get(i).getIsSecondSystem()) {
                    mapSystemType(applicationDataCallDTO, application, i);
                    mapSecondSystemType(application, applicationDataCallDTO, i + 1);
                    i = i + 2;
                } else {
                    mapSystemType(applicationDataCallDTO, application, i);
                    i++;
                }
            } else {
                if (application.getSystemList().get(i).getIsSecondSystem()) {
                    mapWaterHeaterSystemType(application, applicationDataCallDTO, i);
                    mapSecondWaterHeaterSystemType(application, applicationDataCallDTO, i + 1);
                    i = i + 2;

                } else {
                    mapWaterHeaterSystemType(application, applicationDataCallDTO, i);
                    i++;
                }
            }
        }
    }

    private static void mapSecondWaterHeaterSystemType(Application application, ApplicationDataCallDTO applicationDataCallDTO, int i) {
        applicationDataCallDTO.setSecondWaterHeaterSystemType(application.getSystemList().get(i).getSystemType().getSystemTypeName());
    }

    private static void mapWaterHeaterSystemType(Application application, ApplicationDataCallDTO applicationDataCallDTO, int i) {
        applicationDataCallDTO.setWaterHeaterSystemType(application.getSystemList().get(i).getSystemType().getSystemTypeName());
        applicationDataCallDTO.setIsSecondWaterHeaterSystem(application.getSystemList().get(i).getIsSecondSystem());
    }

    private static void mapSecondSystemType(Application application, ApplicationDataCallDTO applicationDataCallDTO, int i) {
        applicationDataCallDTO.setSecondSystemType(application.getSystemList().get(i).getSystemType().getSystemTypeName());
        applicationDataCallDTO.setSecondSystemTonnage(application.getSystemList().get(i).getTonnage());
    }

    private static void mapSystemType(ApplicationDataCallDTO applicationDataCallDTO, Application application, int i) {
        applicationDataCallDTO.setSystemType(application.getSystemList().get(i).getSystemType().getSystemTypeName());
        applicationDataCallDTO.setTonnage(application.getSystemList().get(i).getTonnage());
        applicationDataCallDTO.setIsSecondSystem(application.getSystemList().get(i).getIsSecondSystem());
    }

    private static ApplicantCallDTO getApplicantForSubmitPartnerApplicantCall(Applicant applicant) {
        ApplicantCallDTO applicantCallDTO = new ApplicantCallDTO();
        applicantCallDTO.setNameFirst(applicant.getNameFirst());
        applicantCallDTO.setNameLast(applicant.getNameLast());
        applicantCallDTO.setIdType(applicant.getIdType());
        applicantCallDTO.setCity(applicant.getHomeAddress().getCity());
        applicantCallDTO.setCounty(applicant.getHomeAddress().getCountry());
        applicantCallDTO.setPhoneMobile(applicant.getPhoneMobile());
        applicantCallDTO.setPhoneHome(applicant.getPhoneHome());
        applicantCallDTO.setDateOfBirth(applicant.getDateOfBirth());
        applicantCallDTO.setIdNumber(applicant.getIdNumber());
        applicantCallDTO.setSsn(applicant.getSsn());
        applicantCallDTO.setZipcode(applicant.getHomeAddress().getZipcode());
        applicantCallDTO.setStreet(applicant.getHomeAddress().getStreet());
        applicantCallDTO.setBankRoutingNumber(applicant.getBankRoutingNumber());
        applicantCallDTO.setBankAccountNumber(applicant.getBankAccountingNumber());
        applicantCallDTO.setState(applicant.getHomeAddress().getState());
        applicantCallDTO.setEmail(applicant.getEmail());
        applicantCallDTO.setIdStateIssue(applicant.getHomeAddress().getState());
        applicantCallDTO.setMonthlyIncome(applicant.getMonthlyIncome());
        return applicantCallDTO;
    }

    public String getResponseBodyFromSubmitPartnerApplication(String url, String jsonStr) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?secretKey=" + secretKey + "&dealerKey=" + dealerKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
