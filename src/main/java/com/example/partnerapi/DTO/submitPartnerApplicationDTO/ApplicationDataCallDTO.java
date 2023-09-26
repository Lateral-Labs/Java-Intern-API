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
        "insurance",
        "totalFinancingAmount",
        "propertyStreet",
        "propertyZip",
        "propertyState",
        "totalJobPrice",
        "propertyCity",
        "propertyCounty",
        "partnerAppId",
        "dealerName",
        "salesmanName",
        "salesmanEmail",
        "salesmanPhone",
        "systemType",
        "tonnage",
        "isSecondSystem",
        "secondSystemType",
        "secondSystemTonnage",
        "waterHeaterSystemType",
        "secondWaterHeaterSystemType",
        "isSecondWaterHeaterSystem"
})
@Generated("jsonschema2pojo")
public class ApplicationDataCallDTO {

    @JsonProperty("insurance")
    private Boolean insurance;
    @JsonProperty("totalFinancingAmount")
    private Double totalFinancingAmount;
    @JsonProperty("propertyStreet")
    private String propertyStreet;
    @JsonProperty("propertyZip")
    private String propertyZip;
    @JsonProperty("propertyState")
    private String propertyState;
    @JsonProperty("totalJobPrice")
    private Double totalJobPrice;
    @JsonProperty("propertyCity")
    private String propertyCity;
    @JsonProperty("propertyCounty")
    private String propertyCounty;
    @JsonProperty("partnerAppId")
    private Long partnerAppId;
    @JsonProperty("dealerName")
    private String dealerName;
    @JsonProperty("salesmanName")
    private String salesmanName;
    @JsonProperty("salesmanEmail")
    private String salesmanEmail;
    @JsonProperty("salesmanPhone")
    private String salesmanPhone;
    @JsonProperty("systemType")
    private String systemType;
    @JsonProperty("tonnage")
    private String tonnage;
    @JsonProperty("isSecondSystem")
    private Boolean isSecondSystem;
    @JsonProperty("secondSystemType")
    private String secondSystemType;
    @JsonProperty("secondSystemTonnage")
    private String secondSystemTonnage;
    @JsonProperty("waterHeaterSystemType")
    private String waterHeaterSystemType;
    @JsonProperty("secondWaterHeaterSystemType")
    private String secondWaterHeaterSystemType;
    @JsonProperty("isSecondWaterHeaterSystem")
    private Boolean isSecondWaterHeaterSystem;

    public ApplicationDataCallDTO() {
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("insurance")
    public Boolean getInsurance() {
        return insurance;
    }

    @JsonProperty("insurance")
    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    @JsonProperty("totalFinancingAmount")
    public Double getTotalFinancingAmount() {
        return totalFinancingAmount;
    }

    @JsonProperty("totalFinancingAmount")
    public void setTotalFinancingAmount(Double totalFinancingAmount) {
        this.totalFinancingAmount = totalFinancingAmount;
    }

    @JsonProperty("propertyStreet")
    public String getPropertyStreet() {
        return propertyStreet;
    }

    @JsonProperty("propertyStreet")
    public void setPropertyStreet(String propertyStreet) {
        this.propertyStreet = propertyStreet;
    }

    @JsonProperty("propertyZip")
    public String getPropertyZip() {
        return propertyZip;
    }

    @JsonProperty("propertyZip")
    public void setPropertyZip(String propertyZip) {
        this.propertyZip = propertyZip;
    }

    @JsonProperty("propertyState")
    public String getPropertyState() {
        return propertyState;
    }

    @JsonProperty("propertyState")
    public void setPropertyState(String propertyState) {
        this.propertyState = propertyState;
    }

    @JsonProperty("totalJobPrice")
    public Double getTotalJobPrice() {
        return totalJobPrice;
    }

    @JsonProperty("totalJobPrice")
    public void setTotalJobPrice(Double totalJobPrice) {
        this.totalJobPrice = totalJobPrice;
    }

    @JsonProperty("propertyCity")
    public String getPropertyCity() {
        return propertyCity;
    }

    @JsonProperty("propertyCity")
    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    @JsonProperty("propertyCounty")
    public String getPropertyCounty() {
        return propertyCounty;
    }

    @JsonProperty("propertyCounty")
    public void setPropertyCounty(String propertyCounty) {
        this.propertyCounty = propertyCounty;
    }

    @JsonProperty("partnerAppId")
    public Long getPartnerAppId() {
        return partnerAppId;
    }

    @JsonProperty("partnerAppId")
    public void setPartnerAppId(Long partnerAppId) {
        this.partnerAppId = partnerAppId;
    }

    @JsonProperty("dealerName")
    public String getDealerName() {
        return dealerName;
    }

    @JsonProperty("dealerName")
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    @JsonProperty("salesmanName")
    public String getSalesmanName() {
        return salesmanName;
    }

    @JsonProperty("salesmanName")
    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    @JsonProperty("salesmanEmail")
    public String getSalesmanEmail() {
        return salesmanEmail;
    }

    @JsonProperty("salesmanEmail")
    public void setSalesmanEmail(String salesmanEmail) {
        this.salesmanEmail = salesmanEmail;
    }

    @JsonProperty("salesmanPhone")
    public String getSalesmanPhone() {
        return salesmanPhone;
    }

    @JsonProperty("salesmanPhone")
    public void setSalesmanPhone(String salesmanPhone) {
        this.salesmanPhone = salesmanPhone;
    }

    @JsonProperty("systemType")
    public String getSystemType() {
        return systemType;
    }

    @JsonProperty("systemType")
    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    @JsonProperty("tonnage")
    public String getTonnage() {
        return tonnage;
    }

    @JsonProperty("tonnage")
    public void setTonnage(String tonnage) {
        this.tonnage = tonnage;
    }

    @JsonProperty("isSecondSystem")
    public Boolean getIsSecondSystem() {
        return isSecondSystem;
    }

    @JsonProperty("isSecondSystem")
    public void setIsSecondSystem(Boolean isSecondSystem) {
        this.isSecondSystem = isSecondSystem;
    }

    @JsonProperty("secondSystemType")
    public String getSecondSystemType() {
        return secondSystemType;
    }

    @JsonProperty("secondSystemType")
    public void setSecondSystemType(String secondSystemType) {
        this.secondSystemType = secondSystemType;
    }

    @JsonProperty("secondSystemTonnage")
    public String getSecondSystemTonnage() {
        return secondSystemTonnage;
    }

    @JsonProperty("secondSystemTonnage")
    public void setSecondSystemTonnage(String secondSystemTonnage) {
        this.secondSystemTonnage = secondSystemTonnage;
    }

    @JsonProperty("waterHeaterSystemType")
    public String getWaterHeaterSystemType() {
        return waterHeaterSystemType;
    }

    @JsonProperty("waterHeaterSystemType")
    public void setWaterHeaterSystemType(String waterHeaterSystemType) {
        this.waterHeaterSystemType = waterHeaterSystemType;
    }

    @JsonProperty("secondWaterHeaterSystemType")
    public String getSecondWaterHeaterSystemType() {
        return secondWaterHeaterSystemType;
    }

    @JsonProperty("secondWaterHeaterSystemType")
    public void setSecondWaterHeaterSystemType(String secondWaterHeaterSystemType) {
        this.secondWaterHeaterSystemType = secondWaterHeaterSystemType;
    }

    @JsonProperty("isSecondWaterHeaterSystem")
    public Boolean getIsSecondWaterHeaterSystem() {
        return isSecondWaterHeaterSystem;
    }

    @JsonProperty("isSecondWaterHeaterSystem")
    public void setIsSecondWaterHeaterSystem(Boolean isSecondWaterHeaterSystem) {
        this.isSecondWaterHeaterSystem = isSecondWaterHeaterSystem;
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
