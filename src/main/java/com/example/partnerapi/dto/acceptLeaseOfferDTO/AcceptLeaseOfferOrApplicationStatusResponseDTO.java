package com.example.partnerapi.dto.acceptLeaseOfferDTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.example.partnerapi.dto.submitPartnerApplicationDTO.LeaseOptionDTO;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "applicationId",
        "dealerUserId",
        "rtoNumber",
        "totalJobPrice",
        "totalFinancingAmount",
        "processingStatus",
        "insertedTime",
        "applicationStatus",
        "processingStatusDescription",
        "monthlyPayment",
        "downPaymentAmount",
        "retailCashPrice",
        "partnerAppId",
        "submittedBy",
        "firstPayment",
        "isAgreementSigned",
        "leaseOptions"
})
@Generated("jsonschema2pojo")
public class AcceptLeaseOfferOrApplicationStatusResponseDTO {

    @JsonProperty("applicationId")
    private Long applicationId;
    @JsonProperty("dealerUserId")
    private Long dealerUserId;
    @JsonProperty("rtoNumber")
    private String rtoNumber;
    @JsonProperty("totalJobPrice")
    private Double totalJobPrice;
    @JsonProperty("totalFinancingAmount")
    private Double totalFinancingAmount;
    @JsonProperty("processingStatus")
    private String processingStatus;
    @JsonProperty("insertedTime")
    private String insertedTime;
    @JsonProperty("applicationStatus")
    private String applicationStatus;
    @JsonProperty("processingStatusDescription")
    private String processingStatusDescription;
    @JsonProperty("monthlyPayment")
    private Double monthlyPayment;
    @JsonProperty("downPaymentAmount")
    private Double downPaymentAmount;

    @JsonProperty("retailCashPrice")
    private Double retailCashPrice;
    @JsonProperty("partnerAppId")
    private String partnerAppId;
    @JsonProperty("submittedBy")
    private String submittedBy;
    @JsonProperty("firstPayment")
    private String firstPayment;
    @JsonProperty("isAgreementSigned")
    private Boolean isAgreementSigned;
    @JsonProperty("leaseOptions")
    private List<LeaseOptionDTO> leaseOptions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("applicationId")
    public Long getApplicationId() {
        return applicationId;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    @JsonProperty("dealerUserId")
    public Long getDealerUserId() {
        return dealerUserId;
    }

    @JsonProperty("dealerUserId")
    public void setDealerUserId(Long dealerUserId) {
        this.dealerUserId = dealerUserId;
    }

    @JsonProperty("rtoNumber")
    public String getRtoNumber() {
        return rtoNumber;
    }

    @JsonProperty("rtoNumber")
    public void setRtoNumber(String rtoNumber) {
        this.rtoNumber = rtoNumber;
    }

    @JsonProperty("totalJobPrice")
    public Double getTotalJobPrice() {
        return totalJobPrice;
    }

    @JsonProperty("totalJobPrice")
    public void setTotalJobPrice(Double totalJobPrice) {
        this.totalJobPrice = totalJobPrice;
    }

    @JsonProperty("totalFinancingAmount")
    public Double getTotalFinancingAmount() {
        return totalFinancingAmount;
    }

    @JsonProperty("totalFinancingAmount")
    public void setTotalFinancingAmount(Double totalFinancingAmount) {
        this.totalFinancingAmount = totalFinancingAmount;
    }

    @JsonProperty("processingStatus")
    public String getProcessingStatus() {
        return processingStatus;
    }

    @JsonProperty("processingStatus")
    public void setProcessingStatus(String processingStatus) {
        this.processingStatus = processingStatus;
    }

    @JsonProperty("insertedTime")
    public String getInsertedTime() {
        return insertedTime;
    }

    @JsonProperty("insertedTime")
    public void setInsertedTime(String insertedTime) {
        this.insertedTime = insertedTime;
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

    @JsonProperty("monthlyPayment")
    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    @JsonProperty("monthlyPayment")
    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @JsonProperty("downPaymentAmount")
    public Double getDownPaymentAmount() {
        return downPaymentAmount;
    }

    @JsonProperty("downPaymentAmount")
    public void setDownPaymentAmount(Double downPaymentAmount) {
        this.downPaymentAmount = downPaymentAmount;
    }

    @JsonProperty("retailCashPrice")
    public Double getRetailCashPrice() {
        return retailCashPrice;
    }

    @JsonProperty("retailCashPrice")
    public void setRetailCashPrice(Double retailCashPrice) {
        this.retailCashPrice = retailCashPrice;
    }

    @JsonProperty("partnerAppId")
    public String getPartnerAppId() {
        return partnerAppId;
    }

    @JsonProperty("partnerAppId")
    public void setPartnerAppId(String partnerAppId) {
        this.partnerAppId = partnerAppId;
    }

    @JsonProperty("submittedBy")
    public String getSubmittedBy() {
        return submittedBy;
    }

    @JsonProperty("submittedBy")
    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
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

    public AcceptLeaseOfferOrApplicationStatusResponseDTO() {
    }
}