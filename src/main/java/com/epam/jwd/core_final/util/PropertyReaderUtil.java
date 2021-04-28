package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static PropertyReaderUtil instance = null;

    private static final Properties properties = new Properties();
    private static final String propertiesFileName = "./src/main/resources/application.properties";

    private PropertyReaderUtil() {
    }

    public static PropertyReaderUtil getInstance() {
        if (instance == null) {
            synchronized (PropertyReaderUtil.class) {
                if (instance == null) {
                    instance = new PropertyReaderUtil();
                }
            }
        }
        return instance;
    }

    public String getProperty(String propertyName) {
        loadProperties();
        return properties.getProperty(propertyName);
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    private static void loadProperties() {
        InputStream iStream = null;
        try {
            iStream = new FileInputStream(propertiesFileName);
            properties.load(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (iStream != null) {
                    iStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
