package com.example.partnerapi.dto.dataForANewApplicationDTO;

import java.util.LinkedHashMap;
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
        "nameFirst",
        "nameLast",
        "phoneMobile",
        "phoneHome",
        "dateOfBirth",
        "ssn",
        "bankAccoutingNumber",
        "bankRoutingNumber",
        "email",
        "monthlyIncome",
        "isPrimary",
        "idType"
})
@Generated("jsonschema2pojo")
public class ApplicantDTO {

    @JsonProperty("nameFirst")
    private String nameFirst;
    @JsonProperty("nameLast")
    private String nameLast;
    @JsonProperty("phoneMobile")
    private String phoneMobile;
    @JsonProperty("phoneHome")
    private String phoneHome;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("ssn")
    private String ssn;
    @JsonProperty("bankAccoutingNumber")
    private String bankAccoutingNumber;
    @JsonProperty("bankRoutingNumber")
    private String bankRoutingNumber;
    @JsonProperty("email")
    private String email;
    @JsonProperty("monthlyIncome")
    private Long monthlyIncome;
    @JsonProperty("isPrimary")
    private Boolean isPrimary;
    @JsonProperty("idType")
    private String idType;

    public ApplicantDTO() {
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("nameFirst")
    public String getNameFirst() {
        return nameFirst;
    }

    @JsonProperty("nameFirst")
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    @JsonProperty("nameLast")
    public String getNameLast() {
        return nameLast;
    }

    @JsonProperty("nameLast")
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    @JsonProperty("phoneMobile")
    public String getPhoneMobile() {
        return phoneMobile;
    }

    @JsonProperty("phoneMobile")
    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    @JsonProperty("phoneHome")
    public String getPhoneHome() {
        return phoneHome;
    }

    @JsonProperty("phoneHome")
    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    @JsonProperty("dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("dateOfBirth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("ssn")
    public String getSsn() {
        return ssn;
    }

    @JsonProperty("ssn")
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @JsonProperty("bankAccoutingNumber")
    public String getBankAccoutingNumber() {
        return bankAccoutingNumber;
    }

    @JsonProperty("bankAccoutingNumber")
    public void setBankAccoutingNumber(String bankAccoutingNumber) {
        this.bankAccoutingNumber = bankAccoutingNumber;
    }

    @JsonProperty("bankRoutingNumber")
    public String getBankRoutingNumber() {
        return bankRoutingNumber;
    }

    @JsonProperty("bankRoutingNumber")
    public void setBankRoutingNumber(String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("monthlyIncome")
    public Long getMonthlyIncome() {
        return monthlyIncome;
    }

    @JsonProperty("monthlyIncome")
    public void setMonthlyIncome(Long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @JsonProperty("isPrimary")
    public Boolean getIsPrimary() {
        return isPrimary;
    }

    @JsonProperty("isPrimary")
    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    @JsonProperty("idType")
    public String getIdType() {
        return idType;
    }

    @JsonProperty("idType")
    public void setIdType(String idType) {
        this.idType = idType;
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