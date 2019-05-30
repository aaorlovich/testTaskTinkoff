package ru.tinkoff.orlovich.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Helper {

    private static Logger logger = LoggerFactory.getLogger(Helper.class);

    public static Properties properties;

    public static synchronized Properties getProperties(String path) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(getFileFromResources(path)));
        } catch (IOException e) {
            logger.error("File properties " + path + " not found!!!");
            return null;
        }
        return properties;
    }

    public static synchronized Properties getProperties() {
        if (properties != null) {
            return properties;
        }
        return getProperties("config/test.properties");
    }

    public static File getFileFromResources(String relativePath) {
        ClassLoader loader = Helper.class.getClassLoader();
        URL resource = loader.getResource(relativePath);
        return new File(resource.getFile());
    }

}
