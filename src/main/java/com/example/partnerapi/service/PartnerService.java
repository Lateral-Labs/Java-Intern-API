package com.example.partnerapi.service;

import com.example.partnerapi.dto.LoginDTO;
import com.example.partnerapi.dto.acceptLeaseOfferDTO.AcceptLeaseOfferRequestDTO;
import com.example.partnerapi.dto.applicationStatusDTO.ApplicationStatusResponseDTO;
import com.example.partnerapi.dto.completeWorkDTO.CompleteWorkResponseDTO;
import com.example.partnerapi.dto.completeWorkDTO.CompleteWorkRequestDTO;
import com.example.partnerapi.dto.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.dto.submitPartnerApplicationDTO.SubmitPartnerApplicationResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface PartnerService {

    Long getDataAndSaveANewApplication(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO);

    SubmitPartnerApplicationResponseDTO submitPartnerApplication(Long id) throws IOException, InterruptedException;

    void acceptLeaseOffer(AcceptLeaseOfferRequestDTO acceptLeaseOfferRequestDTO) throws IOException, InterruptedException;


    ApplicationStatusResponseDTO applicationStatus(Long id) throws JsonProcessingException;

    CompleteWorkResponseDTO completeWork(CompleteWorkRequestDTO completeWorkRequestDTO) throws JsonProcessingException;


}

