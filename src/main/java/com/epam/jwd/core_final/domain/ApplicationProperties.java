package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public final class ApplicationProperties {
    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;
    private final String spacemapFileName;
    private final Integer fileRefreshRate;
    private final String dateTimeFormat;
    private final String outputFileName;

    private final PropertyReaderUtil PROPERTY_READER = PropertyReaderUtil.getInstance();

    private static ApplicationProperties instance = null;

    public static ApplicationProperties getInstance() {
        if (instance == null) {
            synchronized (ApplicationProperties.class) {
                if (instance == null) {
                    instance = new ApplicationProperties();
                }
            }
        }
        return instance;
    }

    private ApplicationProperties() {
        this.inputRootDir = PROPERTY_READER.getProperty("inputRootDir");
        this.outputRootDir = PROPERTY_READER.getProperty("outputRootDir");
        this.crewFileName = PROPERTY_READER.getProperty("crewFileName");
        this.missionsFileName = PROPERTY_READER.getProperty("missionsFileName");
        this.spaceshipsFileName = PROPERTY_READER.getProperty("spaceshipsFileName");
        spacemapFileName = PROPERTY_READER.getProperty("spacemapFileName");
        this.fileRefreshRate = Integer.parseInt(PROPERTY_READER.getProperty("fileRefreshRate"));
        this.dateTimeFormat = PROPERTY_READER.getProperty("dateTimeFormat");
        outputFileName = PROPERTY_READER.getProperty("outputFileName");
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getSpacemapFileName() {
        return spacemapFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public String getOutputFileName() {
        return outputFileName;
    }
}
