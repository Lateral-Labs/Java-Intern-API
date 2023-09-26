package com.example.partnerapi.model;

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
    private String status;

    @Column(name = "status_description")
    private String statusDescription;

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
    @JsonManagedReference(value = "application-system")
    private List<System> systemList;

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-salesman")
    private Salesman salesman;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "application-brand")
    private List<Brand> brandList;

    public Application() {
    }

    public List<System> getSystemList() {
        if (systemList == null) {
            systemList = new ArrayList<>();
        }
        return systemList;
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
