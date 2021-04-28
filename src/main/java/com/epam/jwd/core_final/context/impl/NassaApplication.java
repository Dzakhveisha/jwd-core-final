package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum NassaApplication implements Application {
    INSTANCE;

    static final Logger LOGGER = LoggerFactory.getLogger("LOGGER");
    private final ApplicationContext nassaContext = new NassaContext();

    @Override
    public ApplicationMenu start() throws InvalidStateException {

        nassaContext.init();
        final NassaMenu menu = new NassaMenu(nassaContext);
        int result;
        do {
            menu.printAvailableOptions();
            result = menu.handleUserInput();
        } while (result != 0);

        return menu;
    }
}
