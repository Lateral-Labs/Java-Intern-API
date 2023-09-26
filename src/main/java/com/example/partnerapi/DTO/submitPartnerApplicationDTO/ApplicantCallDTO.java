package com.example.partnerapi.DTO.submitPartnerApplicationDTO;

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
        "idType",
        "city",
        "county",
        "phoneMobile",
        "phoneHome",
        "dateOfBirth",
        "idNumber",
        "ssn",
        "zipcode",
        "street",
        "bankRoutingNumber",
        "bankAccountNumber",
        "state",
        "email",
        "idStateIssue",
        "monthlyIncome"
})
@Generated("jsonschema2pojo")
public class ApplicantCallDTO {

    @JsonProperty("nameFirst")
    private String nameFirst;
    @JsonProperty("nameLast")
    private String nameLast;
    @JsonProperty("idType")
    private String idType;
    @JsonProperty("city")
    private String city;
    @JsonProperty("county")
    private String county;
    @JsonProperty("phoneMobile")
    private String phoneMobile;
    @JsonProperty("phoneHome")
    private String phoneHome;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("idNumber")
    private String idNumber;
    @JsonProperty("ssn")
    private String ssn;
    @JsonProperty("zipcode")
    private String zipcode;
    @JsonProperty("street")
    private String street;
    @JsonProperty("bankRoutingNumber")
    private String bankRoutingNumber;
    @JsonProperty("bankAccountNumber")
    private String bankAccountNumber;
    @JsonProperty("state")
    private String state;
    @JsonProperty("email")
    private String email;
    @JsonProperty("idStateIssue")
    private String idStateIssue;
    @JsonProperty("monthlyIncome")
    private Long monthlyIncome;

    public ApplicantCallDTO() {
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

    @JsonProperty("idType")
    public String getIdType() {
        return idType;
    }

    @JsonProperty("idType")
    public void setIdType(String idType) {
        this.idType = idType;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("county")
    public String getCounty() {
        return county;
    }

    @JsonProperty("county")
    public void setCounty(String county) {
        this.county = county;
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

    @JsonProperty("idNumber")
    public String getIdNumber() {
        return idNumber;
    }

    @JsonProperty("idNumber")
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @JsonProperty("ssn")
    public String getSsn() {
        return ssn;
    }

    @JsonProperty("ssn")
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @JsonProperty("zipcode")
    public String getZipcode() {
        return zipcode;
    }

    @JsonProperty("zipcode")
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("bankRoutingNumber")
    public String getBankRoutingNumber() {
        return bankRoutingNumber;
    }

    @JsonProperty("bankRoutingNumber")
    public void setBankRoutingNumber(String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }

    @JsonProperty("bankAccountNumber")
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    @JsonProperty("bankAccountNumber")
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("idStateIssue")
    public String getIdStateIssue() {
        return idStateIssue;
    }

    @JsonProperty("idStateIssue")
    public void setIdStateIssue(String idStateIssue) {
        this.idStateIssue = idStateIssue;
    }

    @JsonProperty("monthlyIncome")
    public Long getMonthlyIncome() {
        return monthlyIncome;
    }

    @JsonProperty("monthlyIncome")
    public void setMonthlyIncome(Long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
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
