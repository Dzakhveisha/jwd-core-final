package com.epam.jwd.core_final.exception;

import java.util.function.Supplier;

public class UnknownEntityException extends RuntimeException implements Supplier<UnknownEntityException> {

    private final String entityName;
    private final Object[] args;

    public UnknownEntityException(String entityName) {
        super();
        this.entityName = entityName;
        this.args = null;
    }

    public UnknownEntityException(String entityName, Object[] args) {
        super();
        this.entityName = entityName;
        this.args = args;
    }

    @Override
    public String getMessage() {
        return entityName + " is unknown!";
    }

    @Override
    public UnknownEntityException get() {
        return this;
    }
}
