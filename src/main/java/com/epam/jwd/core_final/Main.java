package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        try{
            Application app = Application.getInstance();
            app.start();
        }
        catch (Exception es){
            es.printStackTrace();
        }
    }
}