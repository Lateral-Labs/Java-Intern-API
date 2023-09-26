package com.example.partnerapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lease_option")
public class LeaseOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "months")
    private Integer months;

    @Column(name = "payment")
    private Double payment;

    @Column(name = "total_payment")
    private Double totalPayment;

    @Column(name = "is_selected")
    private Boolean isSelected;

    @Column(name = "cost_of_lease_services")
    private Double costOfLeaseServices;

    @ManyToOne
    @JsonBackReference(value = "application-leaseOption")
    @JoinColumn(name = "application_id")
    private Application application;

    public LeaseOption() {
    }
}
