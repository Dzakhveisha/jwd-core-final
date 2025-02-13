package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.Planet;

public interface SpacemapService {

    Planet getRandomPlanet();

    Planet getPlanetByName(String nsme);
    Planet getPlanetByID(long id);

    Planet addPlanetToMap (Planet planet);

    // Dijkstra ?
    int getDistanceBetweenPlanets(Planet first, Planet second);
}
