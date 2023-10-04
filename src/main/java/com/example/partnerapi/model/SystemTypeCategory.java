package com.example.partnerapi.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum SystemTypeCategory {
    HVAC("HVAC"),
    WATER_HEATER("Water Heater");

    private final String stringSystemTypeCategory;

    SystemTypeCategory(String stringSystemTypeCategory) {
        this.stringSystemTypeCategory = stringSystemTypeCategory;
    }

    public static SystemTypeCategory valueOfSystemTypeCategory(String stringSystemTypeCategory1) {
        for (SystemTypeCategory systemTypeCategory : values()) {
            if (systemTypeCategory.stringSystemTypeCategory.equals(stringSystemTypeCategory1)) {
                return systemTypeCategory;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "System Type Category isn't correct.");
    }
    public String getStringSystemTypeCategory() {
        return stringSystemTypeCategory;
    }
}
