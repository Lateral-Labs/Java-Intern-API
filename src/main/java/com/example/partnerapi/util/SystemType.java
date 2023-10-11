package com.example.partnerapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum SystemType {
    HEAR_PUMP_SYSTEM("Heat Pump System"),
    HEAT_PUMP_ONLY("Heat Pump only"),
    AC_SYSTEM("A/C System"),
    AC_ONLY("A/C only"),
    GAS_FURNACE_SYSTEM("Gas Furnace System"),
    GAS_FURNACE_ONLY("Gas Furnace only"),
    AC_PACKAGE("A/C Package"),
    HP_PACKAGE("H/P Package"),
    BOILERS("Boilers"),
    AIR_HANDLER_ONLY("Air Handler"),
    DUCTLESS_MINI_SPLIT_SYSTEM("Ductless Mini Split System"),
    TANK_WATER_HEATER("TANK WATER HEATERS"),
    TANKLESS_WATER_HEATER("TANKLESS WATER HEATERS"),
    HEAT_PUMP_HYBRID_WATER_HEATER("Heat Pump Hybrid Water Heater");

    private final String systemTypeName;

    SystemType(String systemTypeName) {
        this.systemTypeName = systemTypeName;
    }

    public static SystemType valueOfSystemTypeName(String systemTypeName1) {
        for (SystemType systemType : values()) {
            if (systemType.systemTypeName.equals(systemTypeName1)) {
                return systemType;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "System Type isn't correct.");
    }

    public String getSystemTypeName() {
        return systemTypeName;
    }
}
