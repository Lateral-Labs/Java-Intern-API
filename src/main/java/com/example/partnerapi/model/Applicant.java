package com.example.partnerapi.model;

import com.example.partnerapi.dto.dataForANewApplicationDTO.AddressDTO;
import com.example.partnerapi.dto.dataForANewApplicationDTO.ApplicantDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String nameFirst;

    @Column(name = "last_name", nullable = false)
    private String nameLast;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "phone_mobile", nullable = false)
    private String phoneMobile;

    @Column(name = "phone_home")
    private String phoneHome;

    @Column(name = "phone_work")
    private String phoneWork;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "ssn", nullable = false)
    private String ssn;

    @Column(name = "bank_routing_number", nullable = false)
    private String bankRoutingNumber;

    @Column(name = "bank_accounting_number", nullable = false)
    private String bankAccountingNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "employer")
    private String employer;

    @Column(name = "monthly_income", nullable = false)
    private Long monthlyIncome;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary;

    @ManyToOne
    @JsonBackReference(value = "application-applicant")
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "applicant-homeAddress")
    private Address homeAddress;

    public Applicant() {
    }

    public Applicant(ApplicantDTO applicantDTO, AddressDTO addressDTO, Application newApplication) {
        this.nameFirst = applicantDTO.getNameFirst();
        this.nameLast = applicantDTO.getNameLast();
        this.phoneMobile = applicantDTO.getPhoneMobile();
        this.dateOfBirth = applicantDTO.getDateOfBirth();
        this.ssn = applicantDTO.getSsn();
        this.bankAccountingNumber = applicantDTO.getBankAccoutingNumber();
        this.bankRoutingNumber = applicantDTO.getBankRoutingNumber();
        this.email = applicantDTO.getEmail();
        this.monthlyIncome = applicantDTO.getMonthlyIncome();
        this.isPrimary = applicantDTO.getIsPrimary();
        this.homeAddress = new Address(addressDTO, this);
        this.application = newApplication;
    }
}
