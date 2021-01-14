package com.rmi.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ConfigProUtils {

    static Properties properties = null;

    public static void init() {
        //String path = "D:/springCloud/rmi/config.properties";
        String path = "/data/config.properties";
        if (!new File(path).exists()) {
            throw new RuntimeException("not found file " + path);
        }
        Resource resource = new FileSystemResource(path);
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void put(String key, String value) {
        properties.setProperty(key, value);
    }
}
