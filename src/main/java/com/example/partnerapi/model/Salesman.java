package com.example.partnerapi.model;

import com.example.partnerapi.dto.dataForANewApplicationDTO.SalesmanDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "salesman")
public class Salesman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "salesman_name")
    private String salesmanName;

    @Column(name = "salesman_email")
    private String salesmanEmail;

    @Column(name = "salesman_phone")
    private String salesmanPhone;

    @Column(name = "dealer_name")
    private String dealerName;

    @OneToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    @JsonBackReference(value = "application-salesman")
    private Application application;

    public Salesman() {
    }

    public Salesman(SalesmanDTO salesmanDTO, Application newApplication) {
        this.salesmanName = salesmanDTO.getSalesmanName();
        this.salesmanEmail = salesmanDTO.getSalesmanEmail();
        this.salesmanPhone = salesmanDTO.getSalesmanPhone();
        this.dealerName = salesmanDTO.getDealerName();
        this.application = newApplication;
    }

}
