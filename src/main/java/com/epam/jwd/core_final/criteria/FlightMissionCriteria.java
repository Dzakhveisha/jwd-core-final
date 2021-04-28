package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {

    public FlightMissionCriteria searchByMaxDistance(long distanceMax){
        this.predicates.add(mission -> mission.getDistance() <= distanceMax);
        return this;
    }

    public FlightMissionCriteria searchByMinDistance(long distanceMin){
        this.predicates.add(mission -> mission.getDistance() >= distanceMin);
        return this;
    }

    public FlightMissionCriteria searchByStartPlanet(Planet start){
        this.predicates.add(mission -> mission.getFrom().equals(start));
        return this;
    }

    public FlightMissionCriteria searchByEndPlanet(Planet end){
        this.predicates.add(mission -> mission.getTo().equals(end));
        return this;
    }

    public FlightMissionCriteria searchByStartDate(LocalDate date){
        this.predicates.add(mission -> mission.getStartDate().equals(date));
        return this;
    }

    public FlightMissionCriteria searchByStartDuration(long maxDayCount){
        this.predicates.add(mission -> mission.getStartDate().until(mission.getEndDate(), ChronoUnit.DAYS) <= maxDayCount);
        return this;
    }
}
