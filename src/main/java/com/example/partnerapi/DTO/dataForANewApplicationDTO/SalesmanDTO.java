package com.example.partnerapi.DTO.dataForANewApplicationDTO;
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
        "salesmanName",
        "salesmanEmail",
        "salesmanPhone",
        "dealerName"
})
@Generated("jsonschema2pojo")
public class SalesmanDTO {

    @JsonProperty("salesmanName")
    private String salesmanName;
    @JsonProperty("salesmanEmail")
    private String salesmanEmail;
    @JsonProperty("salesmanPhone")
    private String salesmanPhone;
    @JsonProperty("dealerName")
    private String dealerName;

    public SalesmanDTO() {
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

    @JsonProperty("dealerName")
    public String getDealerName() {
        return dealerName;
    }

    @JsonProperty("dealerName")
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
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