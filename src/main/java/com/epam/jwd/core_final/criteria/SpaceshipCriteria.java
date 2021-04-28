package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    public SpaceshipCriteria searchByMaxDistance(long distanceMax){
        this.predicates.add(spaceship -> spaceship.getFlightDistance() <= distanceMax);
        return this;
    }

    public SpaceshipCriteria searchByMinDistance(long distanceMin){
        this.predicates.add(spaceship -> spaceship.getFlightDistance() >= distanceMin);
        return this;
    }

    public SpaceshipCriteria searchByReadiness(){
        this.predicates.add(Spaceship::isReadyForNextMissions);
        return this;
    }
}
