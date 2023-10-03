package com.example.partnerapi.model;

import com.example.partnerapi.DTO.dataForANewApplicationDTO.DataForANewApplicationRequestDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "application_id")
    private Long partnerAppId;

    @Column(name = "status")
    private String status;      //processingStatus

    @Column(name = "status_description")
    private String statusDescription;       //processingStatusDescription

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "reasons")
    private String reasons;

    @Column(name = "total_job_price")
    private Double totalJobPrice;

    @Column(name = "requested_lease_amount")
    private Double requestedLeaseAmount;

    @Column(name = "pre_approved_lease_amount")
    private Double preApprovedLeaseAmount;

    @Column(name = "down_payment")
    private Double downPayment;

    @Column(name = "token")
    private String token;

    @Column(name = "insurance")
    private Boolean insurance;

    @Column(name = "rto_number")
    private String rtoNumber;

    @Column(name = "is_agreement_signed")
    private Boolean isAgreementSigned;

    @Column(name = "first_payment")
    private String firstPayment;

    @Column(name = "inserted_time")
    private String insertedTime;

    @Column(name = "retail_cash_price")
    private Double retailCashPrice;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-applicant")
    private List<Applicant> applicantList;

    @OneToOne(mappedBy = "applicationPropertyAddress", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-propertyAddress")
    @NotNull
    private Address propertyAddress;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-leaseOption")
    private List<LeaseOption> leaseOptionList;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-heaterSystem")
    private List<HeaterSystem> heaterSystemList;

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-salesman")
    private Salesman salesman;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-brand")
    private List<Brand> brandList;

    public Application() {
    }

    public Application(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO) {
        this.salesman = new Salesman(dataForANewApplicationRequestDTO.getSalesman(), this);
        this.propertyAddress = new Address(dataForANewApplicationRequestDTO.getPropertyAddress(), this);
        this.totalJobPrice = dataForANewApplicationRequestDTO.getTotalJobPrice();
        this.insurance = dataForANewApplicationRequestDTO.getInsurance();
        this.applicantList = new ArrayList<>();
        this.applicantList.add(new Applicant(dataForANewApplicationRequestDTO.getPrimaryApplicant(), dataForANewApplicationRequestDTO.getHomeAddressPrimaryApplicant(), this));
        this.applicantList.add(new Applicant(dataForANewApplicationRequestDTO.getCoApplicant(), dataForANewApplicationRequestDTO.getHomeAddressCoApplicant(), this));
        this.heaterSystemList = setDataForSystems(dataForANewApplicationRequestDTO, this);
    }

    private List<HeaterSystem> setDataForSystems(DataForANewApplicationRequestDTO dataForANewApplicationRequestDTO, Application newApplication) {
        List<HeaterSystem> heaterSystems = new ArrayList<>();
        for (int i = 0; i < dataForANewApplicationRequestDTO.getSystemList().size(); i++) {
                heaterSystems.add(new HeaterSystem(dataForANewApplicationRequestDTO, i, newApplication));
        }
        return heaterSystems;
    }

    public List<HeaterSystem> getHeaterSystemList() {
        if (heaterSystemList == null) {
            heaterSystemList = new ArrayList<>();
        }
        return heaterSystemList;
    }

    public List<Brand> getBrandList() {
        if (brandList == null) {
            brandList = new ArrayList<>();
        }
        return brandList;
    }

    public List<Applicant> getApplicantList() {
        if (applicantList == null) {
            applicantList = new ArrayList<>();
        }
        return applicantList;
    }

    public List<LeaseOption> getleaseOptionList() {
        if (leaseOptionList == null) {
            leaseOptionList = new ArrayList<>();
        }
        return leaseOptionList;
    }
}
