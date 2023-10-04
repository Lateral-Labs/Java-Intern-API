package com.example.partnerapi.DTO.completeWorkDTO;

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
        "modelName",
        "systemMake",
        "serialNumber",
        "systemTypeCategory"
})
@Generated("jsonschema2pojo")
public class SelectedBrandDTO {

    @JsonProperty("modelName")
    private String modelName;
    @JsonProperty("systemMake")
    private String systemMake;
    @JsonProperty("serialNumber")
    private String serialNumber;
    @JsonProperty("systemTypeCategory")
    private String systemTypeCategory;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("modelName")
    public String getModelName() {
        return modelName;
    }

    @JsonProperty("modelName")
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @JsonProperty("systemMake")
    public String getSystemMake() {
        return systemMake;
    }

    @JsonProperty("systemMake")
    public void setSystemMake(String systemMake) {
        this.systemMake = systemMake;
    }

    @JsonProperty("serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("serialNumber")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @JsonProperty("systemTypeCategory")
    public String getSystemTypeCategory() {
        return systemTypeCategory;
    }

    @JsonProperty("systemTypeCategory")
    public void setSystemTypeCategory(String systemTypeCategory) {
        this.systemTypeCategory = systemTypeCategory;
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