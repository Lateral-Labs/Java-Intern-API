package com.example.partnerapi.dto.submitPartnerApplicationDTO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "applicationId",
        "status",
        "statusDescription",
        "reasons",
        "totalJobPrice",
        "requestedLeaseAmount",
        "preApprovedLeaseAmount",
        "downPayment",
        "retailCashPrice",
        "leaseOptions",
        "token"
})
@Generated("jsonschema2pojo")
public class SubmitPartnerApplicationResponseDTO {

    @JsonProperty("applicationId")
    private Long applicationId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("statusDescription")
    private String statusDescription;
    @JsonProperty("reasons")
    private String reasons;
    @JsonProperty("totalJobPrice")
    private Integer totalJobPrice;
    @JsonProperty("requestedLeaseAmount")
    private Double requestedLeaseAmount;
    @JsonProperty("preApprovedLeaseAmount")
    private Double preApprovedLeaseAmount;
    @JsonProperty("downPayment")
    private Double downPayment;
    @JsonProperty("retailCashPrice")
    private Double retailCashPrice;
    @JsonProperty("leaseOptions")
    private List<LeaseOptionDTO> leaseOptions;
    @JsonProperty("token")
    private String token;
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

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("statusDescription")
    public String getStatusDescription() {
        return statusDescription;
    }

    @JsonProperty("statusDescription")
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @JsonProperty("reasons")
    public String getReasons() {
        return reasons;
    }

    @JsonProperty("reasons")
    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    @JsonProperty("totalJobPrice")
    public Integer getTotalJobPrice() {
        return totalJobPrice;
    }

    @JsonProperty("totalJobPrice")
    public void setTotalJobPrice(Integer totalJobPrice) {
        this.totalJobPrice = totalJobPrice;
    }

    @JsonProperty("requestedLeaseAmount")
    public Double getRequestedLeaseAmount() {
        return requestedLeaseAmount;
    }

    @JsonProperty("requestedLeaseAmount")
    public void setRequestedLeaseAmount(Double requestedLeaseAmount) {
        this.requestedLeaseAmount = requestedLeaseAmount;
    }

    @JsonProperty("preApprovedLeaseAmount")
    public Double getPreApprovedLeaseAmount() {
        return preApprovedLeaseAmount;
    }

    @JsonProperty("preApprovedLeaseAmount")
    public void setPreApprovedLeaseAmount(Double preApprovedLeaseAmount) {
        this.preApprovedLeaseAmount = preApprovedLeaseAmount;
    }

    @JsonProperty("downPayment")
    public Double getDownPayment() {
        return downPayment;
    }

    @JsonProperty("downPayment")
    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    @JsonProperty("retailCashPrice")
    public Double getRetailCashPrice() {
        return retailCashPrice;
    }

    @JsonProperty("retailCashPrice")
    public void setRetailCashPrice(Double retailCashPrice) {
        this.retailCashPrice = retailCashPrice;
    }

    @JsonProperty("leaseOptions")
    public List<LeaseOptionDTO> getLeaseOptions() {
        return leaseOptions;
    }

    @JsonProperty("leaseOptions")
    public void setLeaseOptions(List<LeaseOptionDTO> leaseOptions) {
        this.leaseOptions = leaseOptions;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
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
