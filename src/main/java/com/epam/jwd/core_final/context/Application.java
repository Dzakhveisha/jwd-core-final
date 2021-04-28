package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaApplication;

public interface Application {

    public static Application getInstance(){
        return NassaApplication.INSTANCE;
    }

    ApplicationMenu start();
}
