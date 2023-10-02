package com.example.partnerapi.model;

import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
@Getter
@Setter
@Table(name = "heater_system")
public class HeaterSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "system_type", nullable = false)
    private SystemType systemType;

    @Column(name = "system_type_category", nullable = false)
    private SystemTypeCategory systemTypeCategory;

    @Column(name = "tonnage")
    private String tonnage;

    @Column(name = "is_second_system")
    private Boolean isSecondSystem;

    @ManyToOne
    @JsonBackReference(value = "application-heaterSystem")
    @JoinColumn(name = "application_id")
    private Application application;

    public HeaterSystem() {
    }

    public HeaterSystem(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO, int i, Application newApplication){
        this.systemType = SystemType.valueOfSystemTypeName(dataForANewApplicationRequestDTO.getSystemList().get(i).getSystemType());
        if (dataForANewApplicationRequestDTO.getSystemList().get(i).getSystemTypeCategory().equals("HVAC")) {
            this.systemTypeCategory = SystemTypeCategory.HVAC;
            this.tonnage = dataForANewApplicationRequestDTO.getSystemList().get(i).getTonnage();
        } else if (dataForANewApplicationRequestDTO.getSystemList().get(i).getSystemTypeCategory().equals("Water Heater")) {
            this.systemTypeCategory = SystemTypeCategory.WATER_HEATER;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "System Type Category isn't correct.");
        }
        this.isSecondSystem = dataForANewApplicationRequestDTO.getSystemList().get(i).getIsSecondSystem();
        this.application = newApplication;
    }

}
