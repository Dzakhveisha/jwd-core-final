package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {

    private  static SpaceshipServiceImpl instance = null;

    private final SpaceshipFactory SPACESHIP_FACTORY = SpaceshipFactory.getInstance();

    private List<Spaceship> spaceshipList = new ArrayList<>();

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            synchronized( SpaceshipServiceImpl.class) {
                if (instance == null) {
                    instance = new SpaceshipServiceImpl();
                }
            }
        }
        return instance;
    }

    private SpaceshipServiceImpl() {}


    @Override
    public List<Spaceship> findAllSpaceships() {
        return spaceshipList;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {

        return spaceshipList.stream().filter((Predicate<? super Spaceship>) criteria.build()).collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return spaceshipList.stream().filter((Predicate<? super Spaceship>) criteria.build()).findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        if (spaceshipList.contains(spaceship)) {
            Spaceship updateSpaceship = SPACESHIP_FACTORY.create(
                    spaceship.getCrew(),
                    spaceship.getFlightDistance() + 200,
                    spaceship.getName()
            );

            spaceshipList.remove(spaceshipList.indexOf(spaceship)); // delete old
            return createSpaceship(createSpaceship(updateSpaceship));
        }
        else {
           throw new UnknownEntityException("Spaceship");
        }
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {

    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        spaceshipList.add(spaceship);
        return spaceship;
    }
}
