package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaApplication;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.context.impl.NassaMenu;
import com.epam.jwd.core_final.exception.InvalidStateException;

public interface Application {

    public static Application getInstance(){
        return NassaApplication.INSTANCE;
    }

    ApplicationMenu start();
}
