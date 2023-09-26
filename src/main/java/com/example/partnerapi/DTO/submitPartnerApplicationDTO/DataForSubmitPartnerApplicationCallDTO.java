package com.example.partnerapi.DTO.submitPartnerApplicationDTO;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.example.partnerapi.DTO.submitPartnerApplicationDTO.ApplicantCallDTO;
import com.example.partnerapi.DTO.submitPartnerApplicationDTO.ApplicationDataCallDTO;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "applicationData",
        "primaryApplicant",
        "coApplicant"
})
@Generated("jsonschema2pojo")
public class DataForSubmitPartnerApplicationCallDTO {

    @JsonProperty("applicationData")
    private ApplicationDataCallDTO applicationData;
    @JsonProperty("primaryApplicant")
    private ApplicantCallDTO primaryApplicant;
    @JsonProperty("coApplicant")
    private ApplicantCallDTO coApplicant;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public DataForSubmitPartnerApplicationCallDTO() {
    }

    public DataForSubmitPartnerApplicationCallDTO(ApplicationDataCallDTO applicationData, ApplicantCallDTO primaryApplicant, ApplicantCallDTO coApplicant) {
        this.applicationData = applicationData;
        this.primaryApplicant = primaryApplicant;
        this.coApplicant = coApplicant;
    }

    public DataForSubmitPartnerApplicationCallDTO(ApplicationDataCallDTO applicationData, ApplicantCallDTO primaryApplicant) {
        this.applicationData = applicationData;
        this.primaryApplicant = primaryApplicant;
    }
    @JsonProperty("applicationData")
    public ApplicationDataCallDTO getApplicationData() {
        return applicationData;
    }

    @JsonProperty("applicationData")
    public void setApplicationData(ApplicationDataCallDTO applicationData) {
        this.applicationData = applicationData;
    }

    @JsonProperty("primaryApplicant")
    public ApplicantCallDTO getPrimaryApplicant() {
        return primaryApplicant;
    }

    @JsonProperty("primaryApplicant")
    public void setPrimaryApplicant(ApplicantCallDTO primaryApplicant) {
        this.primaryApplicant = primaryApplicant;
    }

    @JsonProperty("coApplicant")
    public ApplicantCallDTO getCoApplicant() {
        return coApplicant;
    }

    @JsonProperty("coApplicant")
    public void setCoApplicant(ApplicantCallDTO coApplicant) {
        this.coApplicant = coApplicant;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}