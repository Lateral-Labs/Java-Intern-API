package com.example.partnerapi.DTO.completeWorkDTO;

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
        "applicationId",
        "installDate",
        "contractorInitials",
        "workCompletionDocs",
        "selectedBrands"
})
@Generated("jsonschema2pojo")
public class CompleteWorkRequestDTO {

    @JsonProperty("applicationId")
    private Long applicationId;
    @JsonProperty("installDate")
    private String installDate;
    @JsonProperty("contractorInitials")
    private String contractorInitials;
    @JsonProperty("workCompletionDocs")
    private List<WorkCompletionDocDTO> workCompletionDocs;
    @JsonProperty("selectedBrands")
    private List<SelectedBrandDTO> selectedBrand;
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

    @JsonProperty("installDate")
    public String getInstallDate() {
        return installDate;
    }

    @JsonProperty("installDate")
    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    @JsonProperty("contractorInitials")
    public String getContractorInitials() {
        return contractorInitials;
    }

    @JsonProperty("contractorInitials")
    public void setContractorInitials(String contractorInitials) {
        this.contractorInitials = contractorInitials;
    }

    @JsonProperty("workCompletionDocs")
    public List<WorkCompletionDocDTO> getWorkCompletionDocs() {
        return workCompletionDocs;
    }

    @JsonProperty("workCompletionDocs")
    public void setWorkCompletionDocs(List<WorkCompletionDocDTO> workCompletionDocs) {
        this.workCompletionDocs = workCompletionDocs;
    }

    @JsonProperty("selectedBrands")
    public List<SelectedBrandDTO> getSelectedBrands() {
        return selectedBrand;
    }

    @JsonProperty("selectedBrands")
    public void setSelectedBrands(List<SelectedBrandDTO> selectedBrand) {
        this.selectedBrand = selectedBrand;
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