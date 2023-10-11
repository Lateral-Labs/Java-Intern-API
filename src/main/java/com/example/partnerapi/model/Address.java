package com.example.partnerapi.model;

import com.example.partnerapi.dto.dataForANewApplicationDTO.AddressDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "state", nullable = false)
    private String state;

    @OneToOne
    @JsonBackReference(value = "applicant-homeAddress")
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @OneToOne
    @JsonBackReference(value = "application-propertyAddress")
    @JoinColumn(name = "application_id")
    private Application applicationPropertyAddress;

    public Address() {
    }

    public Address(AddressDTO addressDTO, Applicant newApplicant) {
        this.city = addressDTO.getCity();
        this.zipcode = addressDTO.getZipcode();
        this.street = addressDTO.getStreet();
        this.state = addressDTO.getState();
        this.applicant = newApplicant;
    }

    public Address(AddressDTO addressDTO, Application newApplication) {
        this.city = addressDTO.getCity();
        this.zipcode = addressDTO.getZipcode();
        this.street = addressDTO.getStreet();
        this.state = addressDTO.getState();
        this.applicationPropertyAddress = newApplication;
    }
}
