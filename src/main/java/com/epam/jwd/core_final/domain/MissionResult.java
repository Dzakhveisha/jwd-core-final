package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.MissionFactory;

public enum MissionResult implements BaseEntity {
    CANCELLED(1L),
    FAILED(2L),
    PLANNED(3L),
    IN_PROGRESS(4L),
    COMPLETED(5L);

    private final Long id;

    MissionResult(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public static MissionResult resolveMissionResultById(long id) {
        for (MissionResult result : values()) {
            if (result.getId() == id) {
                return result;
            }
        }
        throw new UnknownEntityException("Impossible to find MissionResult");
    }
}
