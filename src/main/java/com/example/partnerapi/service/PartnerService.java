package com.example.partnerapi.service;

import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferRequestDTO;
import com.example.partnerapi.DTO.applicationStatusDTO.ApplicationStatusResponseDTO;
import com.example.partnerapi.DTO.completeWorkDTO.CompleteWorkResponseDTO;
import com.example.partnerapi.DTO.completeWorkDTO.CompleteWorkRequestDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.SubmitPartnerApplicationResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface PartnerService {

    Long getDataAndSaveANewApplication(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO);

    SubmitPartnerApplicationResponseDTO submitPartnerApplication(Long id) throws IOException, InterruptedException;

    void acceptLeaseOffer(AcceptLeaseOfferRequestDTO acceptLeaseOfferRequestDTO) throws IOException, InterruptedException;


    ApplicationStatusResponseDTO applicationStatus(Long id) throws JsonProcessingException;

    CompleteWorkResponseDTO completeWork(CompleteWorkRequestDTO completeWorkRequestDTO) throws JsonProcessingException;

}

