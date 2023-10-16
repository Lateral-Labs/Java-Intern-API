package com.example.partnerapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum StatusApplicationEnum {
    NEW("NE", "New", ApplicationCategoryEnum.NEW),
    APPROVED_FULL("AF", "Fully Approved", ApplicationCategoryEnum.UNSUBMITTED),
    APPROVED_PARTIAL("AP", "Partially Approved", ApplicationCategoryEnum.UNSUBMITTED),
    COMPLETED("CO", "Completed", ApplicationCategoryEnum.COMPLETE),
    DECISION_PENDING("DP", "Decision Pending", ApplicationCategoryEnum.OPEN),
    DECLINED("DE", "Declined", ApplicationCategoryEnum.MISSED_OPPORTUNITY),
    NEEDS_REVIEW("NR", "Needs Review", ApplicationCategoryEnum.UNSUBMITTED),
    OFFER_REJECTED("RJ", "Offer Rejected", ApplicationCategoryEnum.MISSED_OPPORTUNITY),
    PRE_APPROVED("PA", "Pre-approved", ApplicationCategoryEnum.OPEN),
    RESUBMISSION_PENDING("RS", "Resubmission Pending", ApplicationCategoryEnum.UNSUBMITTED),
    TERMS_ACCEPTED("TA", "Terms Accepted", ApplicationCategoryEnum.UNSUBMITTED),
    CLOSED_WON("CW", "Closed Won", ApplicationCategoryEnum.OPEN);

    private final String statusInitials;
    private final String statusDescription;
    private final ApplicationCategoryEnum applicationCategoryEnum;


    StatusApplicationEnum(String statusInitials, String statusDescription, ApplicationCategoryEnum applicationCategoryEnum) {
        this.statusInitials = statusInitials;
        this.statusDescription = statusDescription;
        this.applicationCategoryEnum = applicationCategoryEnum;
    }

    public static StatusApplicationEnum valueOf(String statusInitials, String statusDescription) {
        for (StatusApplicationEnum statusApplicationEnum : values()) {
            if (statusApplicationEnum.statusInitials.equals(statusInitials) && statusApplicationEnum.statusDescription.equals(statusDescription)) {
                return statusApplicationEnum;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status initials and/or status description are not correct.");
    }

    public String getStatusInitials() {
        return statusInitials;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public ApplicationCategoryEnum getApplicationCategoryEnum() {
        return applicationCategoryEnum;
    }
}
