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
        "systemType",
        "systemTypeCategory",
        "tonnage",
        "isSecondSystem"
})
@Generated("jsonschema2pojo")
public class SystemDTO {

    @JsonProperty("systemType")
    private String systemType;
    @JsonProperty("systemTypeCategory")
    private String systemTypeCategory;
    @JsonProperty("tonnage")
    private String tonnage;
    @JsonProperty("isSecondSystem")
    private Boolean isSecondSystem;

    public SystemDTO() {
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("systemType")
    public String getSystemType() {
        return systemType;
    }

    @JsonProperty("systemType")
    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    @JsonProperty("systemTypeCategory")
    public String getSystemTypeCategory() {
        return systemTypeCategory;
    }

    @JsonProperty("systemTypeCategory")
    public void setSystemTypeCategory(String systemTypeCategory) {
        this.systemTypeCategory = systemTypeCategory;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}