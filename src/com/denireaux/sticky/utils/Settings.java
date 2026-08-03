package com.denireaux.sticky.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.InputStream;

public class Settings {

    public static Map<String, String> loadConfig(String fileName) {
        Map<String, String> configMap = new HashMap<>();
        Properties properties = new Properties();

        try (InputStream input = Settings.class.getClassLoader().getResourceAsStream(fileName)) {
            
            if (input == null) {
                System.err.println("Error: Unable to find file " + fileName);
                return configMap;
            }

            properties.load(input);
            for (String key : properties.stringPropertyNames()) configMap.put(key, properties.getProperty(key));

        } catch (IOException ex) {
            System.err.println("Error reading the properties file.");
            ex.printStackTrace();
        }

        return configMap;
    }

    public static void main(String[] args) {
        Map<String, String> settings = loadConfig("application.properties");
        System.out.println(settings.values());
    }
   
}
