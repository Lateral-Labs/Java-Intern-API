package com.example.partnerapi.model;

import com.example.partnerapi.DTO.completeWorkDTO.SelectedBrandDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "selected_brand")
public class SelectedBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "system_make")
    private String systemMake;

    @Column(name = "serial_number")
    private String serialNumber;

    //enum
    @Column(name = "system_type_category")
    private SystemTypeCategory systemTypeCategory;

    @ManyToOne
    @JsonBackReference(value = "application-selectedBrand")
    @JoinColumn(name = "application_id")
    private Application application;

    public SelectedBrand() {
    }

    public SelectedBrand(SelectedBrandDTO selectedBrandDTO, Application application) {
        this.modelName = selectedBrandDTO.getModelName();
        this.systemMake = selectedBrandDTO.getSystemMake();
        this.serialNumber = selectedBrandDTO.getSerialNumber();
        this.systemTypeCategory = SystemTypeCategory.valueOfSystemTypeCategory(selectedBrandDTO.getSystemTypeCategory());
    }

}
