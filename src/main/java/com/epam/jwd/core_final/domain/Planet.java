package com.epam.jwd.core_final.domain;

import java.awt.Point;
import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * location could be a simple class Point with 2 coordinates
 */
public class Planet extends AbstractBaseEntity{
    private Point location;
    private static long idCounter = 0;

    public Planet(Point location, String name) {
        super(name);
        this.location = location;
        this.id = ++ idCounter;
    }

    public Point getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        Planet planet = (Planet) o;
        return Objects.equals(location, planet.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name=" + getName() +
                ", location=(" + location.x  + ", " + location.y + ")}";
    }
}
