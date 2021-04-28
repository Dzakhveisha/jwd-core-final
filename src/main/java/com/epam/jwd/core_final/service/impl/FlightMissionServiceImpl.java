package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.MissionFactory;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpacemapService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightMissionServiceImpl implements MissionService {

    private static FlightMissionServiceImpl instance = null;

    private final MissionFactory MISSION_FACTORY = MissionFactory.getInstance();
    private final SpacemapService SPACEMAP_SERVICE = SpacemapServiceImpl.getInstance();

    private List<FlightMission> missionList = new ArrayList<>();

    public static FlightMissionServiceImpl getInstance() {
        if (instance == null) {
            synchronized( FlightMissionServiceImpl.class) {
                if (instance == null) {
                    instance = new FlightMissionServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return missionList;
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return missionList.stream().filter((Predicate<? super FlightMission>) criteria.build()).collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return missionList.stream().filter((Predicate<? super FlightMission>) criteria.build()).findFirst();
    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        if (missionList.contains(flightMission)) {
            Planet start = SPACEMAP_SERVICE.getRandomPlanet();
            Planet end = SPACEMAP_SERVICE.getRandomPlanet();
            while (end == start) {
                end = SPACEMAP_SERVICE.getRandomPlanet();
            }
            FlightMission updateMission = MISSION_FACTORY.create(
                    flightMission.getName(), LocalDate.now(),
                    LocalDate.now().plusMonths(1 + (int) (Math.random() * 60)), start, end
            );
            missionList.remove(missionList.indexOf(flightMission)); // delete old
            return createMission(updateMission);
        }
        else {
            // throw custom exception
        }
        return null;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        //validation
        // custom exception

        missionList.add(flightMission);
        return flightMission;
    }
}
