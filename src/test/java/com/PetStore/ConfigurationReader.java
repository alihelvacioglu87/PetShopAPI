package com.PetStore;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static FileInputStream file;
    private static Properties properties;

    static {

        try {
            file = new FileInputStream("configuration.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getProperty(String Keyword){
        return properties.getProperty(Keyword);
    }

}
