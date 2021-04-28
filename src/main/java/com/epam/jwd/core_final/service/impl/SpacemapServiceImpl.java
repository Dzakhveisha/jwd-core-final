package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.ArrayList;
import java.util.List;

public class SpacemapServiceImpl implements SpacemapService {

    private  static SpacemapServiceImpl instance = null;

    private List<Planet> planetList = new ArrayList<>();

    public static SpacemapServiceImpl getInstance() {
        if (instance == null) {
            synchronized( SpacemapServiceImpl.class) {
                if (instance == null) {
                    instance = new SpacemapServiceImpl();
                }
            }
        }
        return instance;
    }

    private SpacemapServiceImpl() {}

    @Override
    public Planet getRandomPlanet() {

        return planetList.get((int)(Math.random() * (planetList.size() - 1)) );
    }

    @Override
    public Planet getPlanetByName(String name) {
        return planetList.stream().filter(planet -> planet.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Planet getPlanetByID(long id) {
        return planetList.stream().filter(planet -> planet.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Planet addPlanetToMap(Planet planet) {
        //validation
        // custom exception

        planetList.add(planet);
        return planet;
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {        // sorry, but it's not Dijkstra :(
        return (int) first.getLocation().distance(second.getLocation());
    }

}
