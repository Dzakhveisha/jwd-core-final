package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;

public class MissionFactory implements EntityFactory<FlightMission> {

    private static MissionFactory instance = null;

    @Override
    public FlightMission create(Object... args) {
        return new FlightMission((String) args[0], (LocalDate) args[1], (LocalDate) args[2], (Planet) args[3], (Planet) args[4]);
    }

    public static MissionFactory getInstance() {
        if (instance == null) {
            synchronized (MissionFactory.class) {
                if (instance == null) {
                    instance = new MissionFactory();
                }
            }
        }
        return instance;
    }

}
