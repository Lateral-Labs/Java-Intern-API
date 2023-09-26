package com.example.partnerapi.DTO.dataForANewApplicationDTO;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "salesman",
        "systemList",
        "primaryApplicant",
        "coApplicant",
        "propertyAddress",
        "homeAddressPrimaryApplicant",
        "homeAddressCoApplicant",
        "totalJobPrice",
        "insurance"
})

@Generated("jsonschema2pojo")
public class DataForANewApplicationRequestDTO {

    @JsonProperty("salesman")
    private SalesmanDTO salesman;
    @JsonProperty("systemList")
    private List<SystemDTO> systemList;
    @JsonProperty("primaryApplicant")
    private ApplicantDTO primaryApplicant;
    @JsonProperty("coApplicant")
    private ApplicantDTO coApplicant;
    @JsonProperty("propertyAddress")
    private AddressDTO propertyAddress;
    @JsonProperty("homeAddressPrimaryApplicant")
    private AddressDTO homeAddressPrimaryApplicant;
    @JsonProperty("homeAddressCoApplicant")
    private AddressDTO homeAddressCoApplicant;
    @JsonProperty("totalJobPrice")
    private Double totalJobPrice;
    @JsonProperty("insurance")
    private Boolean insurance;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public DataForANewApplicationRequestDTO() {
    }

    @JsonProperty("salesman")
    public SalesmanDTO getSalesman() {
        return salesman;
    }

    @JsonProperty("salesman")
    public void setSalesman(SalesmanDTO salesman) {
        this.salesman = salesman;
    }

    @JsonProperty("systemList")
    public List<SystemDTO> getSystemList() {
        return systemList;
    }

    @JsonProperty("systemList")
    public void setSystemList(List<SystemDTO> systemList) {
        this.systemList = systemList;
    }

    @JsonProperty("primaryApplicant")
    public ApplicantDTO getPrimaryApplicant() {
        return primaryApplicant;
    }

    @JsonProperty("primaryApplicant")
    public void setPrimaryApplicant(ApplicantDTO primaryApplicant) {
        this.primaryApplicant = primaryApplicant;
    }

    @JsonProperty("coApplicant")
    public ApplicantDTO getCoApplicant() {
        return coApplicant;
    }

    @JsonProperty("coApplicant")
    public void setCoApplicant(ApplicantDTO coApplicant) {
        this.coApplicant = coApplicant;
    }

    @JsonProperty("propertyAddress")
    public AddressDTO getPropertyAddress() {
        return propertyAddress;
    }

    @JsonProperty("propertyAddress")
    public void setPropertyAddress(AddressDTO propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    @JsonProperty("homeAddressPrimaryApplicant")
    public AddressDTO getHomeAddressPrimaryApplicant() {
        return homeAddressPrimaryApplicant;
    }

    @JsonProperty("homeAddressPrimaryApplicant")
    public void setHomeAddressPrimaryApplicant(AddressDTO homeAddressPrimaryApplicant) {
        this.homeAddressPrimaryApplicant = homeAddressPrimaryApplicant;
    }

    @JsonProperty("homeAddressCoApplicant")
    public AddressDTO getHomeAddressCoApplicant() {
        return homeAddressCoApplicant;
    }

    @JsonProperty("homeAddressCoApplicant")
    public void setHomeAddressCoApplicant(AddressDTO homeAddressCoApplicant) {
        this.homeAddressCoApplicant = homeAddressCoApplicant;
    }

    @JsonProperty("totalJobPrice")
    public Double getTotalJobPrice() {
        return totalJobPrice;
    }

    @JsonProperty("totalJobPrice")
    public void setTotalJobPrice(Double totalJobPrice) {
        this.totalJobPrice = totalJobPrice;
    }

    @JsonProperty("insurance")
    public Boolean getInsurance() {
        return insurance;
    }

    @JsonProperty("insurance")
    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
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
