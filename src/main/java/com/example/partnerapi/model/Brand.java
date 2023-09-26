package com.example.partnerapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "system")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "model_name")
    private String modelName;

    @Column (name = "system_make")
    private String systemMake;

    @Column (name = "serial_number")
    private String serialNumber;

    //enum
    @Column (name = "system_type_category")
    private SystemTypeCategory systemTypeCategory;

    @ManyToOne
    @JsonBackReference(value = "application-brand")
    @JoinColumn(name = "application_id")
    private Application application;

    public Brand() {
    }

}
