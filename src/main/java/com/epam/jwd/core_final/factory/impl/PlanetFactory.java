package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.awt.Point;

public class PlanetFactory implements EntityFactory<Planet> {

    private static PlanetFactory instance = null;

    private PlanetFactory(){ }

    @Override
    public Planet create(Object... args) {
        return new Planet((Point)args[0], (String)args[1]);
    }

    public static PlanetFactory getInstance() {
        if (instance == null) {
            synchronized(PlanetFactory.class) {
                if (instance == null) {
                    instance = new PlanetFactory();
                }
            }
        }
        return instance;
    }
}
