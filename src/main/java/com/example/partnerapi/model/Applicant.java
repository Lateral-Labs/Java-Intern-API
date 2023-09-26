package com.example.partnerapi.model;

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

    @Column(name="first_name", nullable = false)
    private String nameFirst;

    @Column(name="last_name", nullable = false)
    private String nameLast;


    //id type ??? exp "Drivers License"
    @Column(name = "id_type")
    private String idType;

    @Column (name = "phone_mobile", nullable = false)
    private String phoneMobile;

    @Column (name = "phone_home")
    private String phoneHome;

    @Column (name = "phone_work")
    private String phoneWork;

    @Column (name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column (name = "id_number")
    private String idNumber;

    // social security administration
    @Column (name = "ssn", nullable = false)
    private String ssn;

    @Column (name = "bank_routing_number", nullable = false)
    private String bankRoutingNumber;

    @Column (name = "bank_accounting_number", nullable = false)
    private String bankAccountingNumber;

    @Column (name = "email", nullable = false)
    private String email;

    @Column (name = "employer")
    private String employer;

    @Column (name = "monthly_income", nullable = false)
    private Long monthlyIncome;

    @Column (name = "is_primary", nullable = false)
    private Boolean isPrimary;

//    @OneToOne
//    @JsonBackReference(value = "application-primaryApplicant")
//    @JoinColumn(name = "applicationPrimaryApplicant_id")
//    private Application applicationPrimaryApplicant;
//
//    @OneToOne
//    @JsonBackReference(value = "application-coApplicant")
//    @JoinColumn(name = "applicationCoApplicant_id")
//    private Application applicationCoApplicant;

    @ManyToOne
    @JsonBackReference(value = "application-applicant")
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "applicant-homeAddress")
    private Address homeAddress;

    public Applicant() {
    }


}
