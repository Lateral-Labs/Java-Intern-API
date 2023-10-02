package com.example.partnerapi.DTO.acceptLeaseOfferDTO;

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
        "applicationId",
        "acceptedTerm"
})
@Generated("jsonschema2pojo")
public class AcceptLeaseOfferRequestDTO {

    @JsonProperty("applicationId")
    private Long applicationId;
    @JsonProperty("acceptedTerm")
    private Integer acceptedTerm;
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

    @JsonProperty("acceptedTerm")
    public Integer getAcceptedTerm() {
        return acceptedTerm;
    }

    @JsonProperty("acceptedTerm")
    public void setAcceptedTerm(Integer acceptedTerm) {
        this.acceptedTerm = acceptedTerm;
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