package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {

    private Map<Role, Short> crew;
    private long flightDistance;
    private boolean isReadyForNextMissions;
    private static long idCounter = 0;

    public Spaceship(Map<Role, Short> crew, long flightDistance, String name) {
        super(name);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = true;
        this.id = ++idCounter;

    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public long getFlightDistance() {
        return flightDistance;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spaceship)) return false;
        Spaceship spaceship = (Spaceship) o;
        return flightDistance == spaceship.flightDistance && isReadyForNextMissions == spaceship.isReadyForNextMissions && Objects.equals(crew, spaceship.crew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, flightDistance, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return "Spaceship " +
                "crew=" + crew +
                ", flightDistance=" + flightDistance +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '\n';
    }
}
