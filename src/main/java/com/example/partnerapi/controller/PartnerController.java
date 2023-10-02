package com.example.partnerapi.controller;

import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferRequestDTO;
import com.example.partnerapi.DTO.acceptLeaseOfferDTO.AcceptLeaseOfferResponseDTO;
import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.SubmitPartnerApplicationResponseDTO;
import com.example.partnerapi.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("partner")
public class PartnerController {

    private PartnerService partnerService;

    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping("addDataForANewApplication")
    public Long submitDataForANewApplication(@RequestBody DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO) {
        return partnerService.getDataAndSaveANewApplication(dataForANewApplicationRequestDTO);
    }

    @PostMapping("submitPartnerApplication/{id}")
    public ResponseEntity<SubmitPartnerApplicationResponseDTO> submitPartnerApplication(@PathVariable("id") Long id) throws IOException, InterruptedException {
        return status(HttpStatus.OK).body(partnerService.submitPartnerApplication(id));
    }

    @PostMapping("acceptLeaseOffer")
    public void acceptLeaseOffer(@RequestBody AcceptLeaseOfferRequestDTO acceptLeaseOfferRequestDTO) throws IOException, InterruptedException {
        partnerService.acceptLeaseOffer(acceptLeaseOfferRequestDTO);
    }


}
