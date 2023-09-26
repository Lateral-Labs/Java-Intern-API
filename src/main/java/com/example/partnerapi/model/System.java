package com.example.partnerapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "system")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "system_type", nullable = false)
    private SystemType systemType;

    @Column (name = "system_type_category", nullable = false)
    private SystemTypeCategory systemTypeCategory;

    @Column (name = "tonnage")
    private String tonnage;

    @Column (name = "is_second_system")
    private Boolean isSecondSystem;

    @ManyToOne
    @JsonBackReference(value = "application-system")
    @JoinColumn(name = "application_id")
    private Application application;

    public System() {
    }
}
