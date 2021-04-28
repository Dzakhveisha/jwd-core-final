package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {

    private static SpaceshipFactory instance = null;

    private SpaceshipFactory(){ }

    @Override
    public Spaceship create(Object... args) {
        return new Spaceship((Map<Role, Short>) args[0], (long)args[1], (String)args[2] );
    }

    public static SpaceshipFactory getInstance() {
        if (instance == null) {
            synchronized(SpaceshipFactory.class) {
                if (instance == null) {
                    instance = new SpaceshipFactory();
                }
            }
        }
        return instance;
    }
}
