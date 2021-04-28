package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.FlightMission;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonConverter {

    private static JsonConverter instance;

    private final File outputFile = new File(getClass().getClassLoader().getResource(ApplicationProperties.getInstance().getOutputRootDir()
            + "/" + ApplicationProperties.getInstance().getOutputFileName()).getFile());


    private JsonConverter(){}

    public static JsonConverter getInstance(){
        if (instance == null) {
            synchronized (JsonConverter.class) {
                if (instance == null) {
                    instance = new JsonConverter();
                }
            }
        }
        return instance;
    }

    public void toJSON(FlightMission mission){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputFile, mission);
            System.out.println("json created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FlightMission toJavaObject() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(outputFile, FlightMission.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
