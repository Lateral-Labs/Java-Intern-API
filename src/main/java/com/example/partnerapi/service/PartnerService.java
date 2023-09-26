package com.example.partnerapi.service;

import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.DataForSubmitPartnerApplicationCallDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.SubmitPartnerApplicationResponseDTO;
import com.example.partnerapi.model.Application;

import java.io.IOException;

public interface PartnerService {

    Long getDataAndSaveANewApplication(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO);

    SubmitPartnerApplicationResponseDTO submitPartnerApplication(Long id) throws IOException, InterruptedException;
}
