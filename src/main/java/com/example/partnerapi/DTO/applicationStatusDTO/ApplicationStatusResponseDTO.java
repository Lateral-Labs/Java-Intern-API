package com.example.partnerapi.DTO.applicationStatusDTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.example.partnerapi.DTO.submitPartnerApplicationDTO.LeaseOptionDTO;
import com.example.partnerapi.model.Application;
import com.example.partnerapi.model.LeaseOption;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NonNull;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "processingStatus",
        "applicationStatus",
        "processingStatusDescription",
        "downPaymentAmount",
        "firstPayment",
        "isAgreementSigned",
        "leaseOptions"
})
@Generated("jsonschema2pojo")
public class ApplicationStatusResponseDTO {

    @JsonProperty("processingStatus")
    private String processingStatus;
    @JsonProperty("applicationStatus")
    private String applicationStatus;
    @JsonProperty("processingStatusDescription")
    private String processingStatusDescription;
    @JsonProperty("downPaymentAmount")
    private Double downPaymentAmount;
    @JsonProperty("firstPayment")
    private String firstPayment;
    @JsonProperty("isAgreementSigned")
    private Boolean isAgreementSigned;
    @JsonProperty("leaseOptions")
    private List<LeaseOptionDTO> leaseOptions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public ApplicationStatusResponseDTO() {
    }

    public ApplicationStatusResponseDTO(Application application) {
        this.applicationStatus = application.getApplicationStatus();
        this.processingStatus = application.getStatus();
        this.processingStatusDescription = application.getStatusDescription();
        this.downPaymentAmount = application.getDownPayment();
        this.firstPayment = application.getFirstPayment();
        this.isAgreementSigned = application.getIsAgreementSigned();
        this.leaseOptions = new ArrayList<>();
        for (LeaseOption leaseOption : application.getleaseOptionList()) {
            if (leaseOption.getIsSelected()) {
                this.leaseOptions.add(new LeaseOptionDTO(leaseOption));
            }
        }

    }

    @JsonProperty("processingStatus")
    public String getProcessingStatus() {
        return processingStatus;
    }

    @JsonProperty("processingStatus")
    public void setProcessingStatus(String processingStatus) {
        this.processingStatus = processingStatus;
    }

    @JsonProperty("applicationStatus")
    public String getApplicationStatus() {
        return applicationStatus;
    }

    @JsonProperty("applicationStatus")
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @JsonProperty("processingStatusDescription")
    public String getProcessingStatusDescription() {
        return processingStatusDescription;
    }

    @JsonProperty("processingStatusDescription")
    public void setProcessingStatusDescription(String processingStatusDescription) {
        this.processingStatusDescription = processingStatusDescription;
    }

    @JsonProperty("downPaymentAmount")
    public Double getDownPaymentAmount() {
        return downPaymentAmount;
    }

    @JsonProperty("downPaymentAmount")
    public void setDownPaymentAmount(Double downPaymentAmount) {
        this.downPaymentAmount = downPaymentAmount;
    }

    @JsonProperty("firstPayment")
    public String getFirstPayment() {
        return firstPayment;
    }

    @JsonProperty("firstPayment")
    public void setFirstPayment(String firstPayment) {
        this.firstPayment = firstPayment;
    }

    @JsonProperty("isAgreementSigned")
    public Boolean getIsAgreementSigned() {
        return isAgreementSigned;
    }

    @JsonProperty("isAgreementSigned")
    public void setIsAgreementSigned(Boolean isAgreementSigned) {
        this.isAgreementSigned = isAgreementSigned;
    }

    @JsonProperty("leaseOptions")
    public List<LeaseOptionDTO> getLeaseOptions() {
        return leaseOptions;
    }

    @JsonProperty("leaseOptions")
    public void setLeaseOptions(List<LeaseOptionDTO> leaseOptions) {
        this.leaseOptions = leaseOptions;
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
