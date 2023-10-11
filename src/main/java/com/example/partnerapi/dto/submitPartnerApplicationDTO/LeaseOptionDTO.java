package com.example.partnerapi.dto.submitPartnerApplicationDTO;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.example.partnerapi.model.LeaseOption;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "months",
        "payment",
        "totalPayment",
        "isSelected",
        "costOfLeaseServices"
})
@Generated("jsonschema2pojo")
public class LeaseOptionDTO {

    @JsonProperty("months")
    private Integer months;
    @JsonProperty("payment")
    private Double payment;
    @JsonProperty("totalPayment")
    private Double totalPayment;
    @JsonProperty("isSelected")
    private Boolean isSelected;
    @JsonProperty("costOfLeaseServices")
    private Double costOfLeaseServices;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public LeaseOptionDTO() {
    }

    public LeaseOptionDTO(LeaseOption leaseOption) {
        this.months = leaseOption.getMonths();
        this.payment = leaseOption.getPayment();
        this.totalPayment = leaseOption.getTotalPayment();
        this.isSelected = leaseOption.getIsSelected();
        this.costOfLeaseServices = leaseOption.getCostOfLeaseServices();
    }

    @JsonProperty("months")
    public Integer getMonths() {
        return months;
    }

    @JsonProperty("months")
    public void setMonths(Integer months) {
        this.months = months;
    }

    @JsonProperty("payment")
    public Double getPayment() {
        return payment;
    }

    @JsonProperty("payment")
    public void setPayment(Double payment) {
        this.payment = payment;
    }

    @JsonProperty("totalPayment")
    public Double getTotalPayment() {
        return totalPayment;
    }

    @JsonProperty("totalPayment")
    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    @JsonProperty("isSelected")
    public Boolean getIsSelected() {
        return isSelected;
    }

    @JsonProperty("isSelected")
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    @JsonProperty("costOfLeaseServices")
    public Double getCostOfLeaseServices() {
        return costOfLeaseServices;
    }

    @JsonProperty("costOfLeaseServices")
    public void setCostOfLeaseServices(Double costOfLeaseServices) {
        this.costOfLeaseServices = costOfLeaseServices;
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