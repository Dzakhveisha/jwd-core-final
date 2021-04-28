package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.service.impl.SpacemapServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 * from {@link Planet}
 * to {@link Planet}
 */
public class FlightMission extends AbstractBaseEntity {

    private String missionsName;
    private LocalDate startDate;
    private LocalDate endDate;
    private long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;
    private Planet from;
    private Planet to;
    private static long idCounter = 0;

    public FlightMission(String name, LocalDate startDate, LocalDate endDate, Planet PlanetFrom, Planet PlanetTo) {
        super(name);
        this.missionsName = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.from = PlanetFrom;
        this.to = PlanetTo;
        this.distance = SpacemapServiceImpl.getInstance().getDistanceBetweenPlanets(from, to);
        this.id = ++ idCounter;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getDistance() {
        return distance;
    }

    public Planet getFrom() {
        return from;
    }

    public Planet getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightMission)) return false;
        FlightMission that = (FlightMission) o;
        return distance == that.distance && Objects.equals(missionsName, that.missionsName) &&
                Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) &&
                Objects.equals(assignedSpaceShift, that.assignedSpaceShift) && Objects.equals(assignedCrew, that.assignedCrew) &&
                missionResult == that.missionResult && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionsName, startDate, endDate, distance, assignedSpaceShift, assignedCrew, missionResult, from, to);
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                "missionsName='" + missionsName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", distance=" + distance + "\n" +
                "   assignedSpaceShift=" + assignedSpaceShift +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                ",\n from=" + from +
                ", to=" + to +
                '\n';
    }
}
