package ru.tinkoff.orlovich.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author Orlovich Artem
 * <p>
 * Утилитарный класс, помогает в работе со внешним конфигурационным файлом и получением файла по относительному пути
 */

public class HelperUtils {

    //logger
    private static Logger logger = LoggerFactory.getLogger(HelperUtils.class);

    //глобальное поле настроек
    public static Properties properties;

    /*
     * Функция позволяет загружать и получать настройки из внешнего файла,
     * инициализирует внутреннее глобальное поле properties
     * */
    public static synchronized Properties getProperties(String path) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(getFileFromResources(path)));
        } catch (IOException e) {
            logger.error("File properties " + path + " not found!!!");
        }
        return properties;
    }

    /*
     * функция получения настроек(инициализированный предыдущим методом)
     * если не инициализированы то пытается получить файл конфигурации по умолчанию
     * */

    public static synchronized Properties getProperties() {
        if (properties != null) {
            return properties;
        }
        return getProperties("config/test.properties");
    }

    /*
     * Поулучение файла из ресурсов приложения по относительному пути
     * */
    public static File getFileFromResources(String relativePath) {
        URL resource = null;
        try {
            ClassLoader loader = HelperUtils.class.getClassLoader();
            resource = loader.getResource(relativePath);
        } catch (Exception e) {
            logger.error("File not found (");
        }
        return new File(resource.getFile());
    }

}
