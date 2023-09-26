package com.example.partnerapi.model;

public enum SystemTypeCategory {
    HVAC("HVAC"),
    WATER_HEATER("Water Heater");

    private String systemType;

    SystemTypeCategory(String stringSystemType) {
        this.systemType = stringSystemType;
    }

    public String getSystemType() {
        return systemType;
    }
}
