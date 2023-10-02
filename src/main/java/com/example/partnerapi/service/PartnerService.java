package com.example.partnerapi.service;

import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferRequestDTO;
import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferResponseDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.DataForSubmitPartnerApplicationCallDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.SubmitPartnerApplicationResponseDTO;
import com.example.partnerapi.model.Application;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PartnerService {

    Long getDataAndSaveANewApplication(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO);

    SubmitPartnerApplicationResponseDTO submitPartnerApplication(Long id) throws IOException, InterruptedException;

    void acceptLeaseOffer(AcceptLeaseOfferRequestDTO acceptLeaseOfferRequestDTO) throws IOException, InterruptedException;


}
