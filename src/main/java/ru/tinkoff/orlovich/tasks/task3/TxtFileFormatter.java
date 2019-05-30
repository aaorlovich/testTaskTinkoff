package ru.tinkoff.orlovich.tasks.task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static ru.tinkoff.orlovich.commons.utils.HelperUtils.getFileFromResources;

/**
 * @author Orlovich Artem
 * <p>
 * Задача 3
 * Задача про форматирование файла (Java8)
 * Дан файл с расширением .txt. В файле две строки – первая колонки, вторая значения колонок.
 * Разделитель между значениями в строках – табуляция или “;”. Напишите функцию на вход которой
 * подается колонка и ее новое значение, на которое нужно заменить старое значение в файле.
 * Примечание – файл должен быть создан и лежать в проекте в ресурсах.
 * <p>
 * Класс является по своей сути одноразовым и работает только с одним файлом
 * принимает в конструторе относительный путь до текстового файла
 * а так же может принмать текстовый разделитель(в принцепи любой) и специфичный разделитель линий
 * основной рабочий метод replaceValueOfColumn
 */
public class TxtFileFormatter {

    //файл с которым будем работать
    private final File file;
    //логгер
    Logger logger = LoggerFactory.getLogger(TxtFileFormatter.class);

    //разделитель колонок
    private String delimiter = ";";
    //разделитель линий
    private String lineSeparator = System.lineSeparator();
    //работаем с кэшем, текста мало модно и в память загружать и изменять
    private StringBuilder cache = new StringBuilder();


    public TxtFileFormatter(File file) {
        this.file = file;
    }

    public TxtFileFormatter(String filePath) {
        this.file = getFileFromResources(filePath);
    }


    public TxtFileFormatter(String filePath, String delimiter, String lineSeparator) {
        this.file = getFileFromResources(filePath);
        this.delimiter = delimiter;
        this.lineSeparator = lineSeparator;
    }

    /*
     * Основной метод замещающий значение в колонке
     * на вход подается точное название колонки, и вторым параметром новое значение
     *
     * сначала получаем содержимое файла в виде строки
     * по регулярке находим искомую строку и заменяем значение этой строки
     * после чего перезаписываем файл с новым содержимым
     *
     * */
    public void replaceValueOfColumn(String columnName, String newValue) {
        String oldFileContent = getStringFromFile(file);
        String regexp = columnName + delimiter + ".*\\s";
        String newLine = columnName + delimiter + newValue;
        String newFileContent = oldFileContent.replaceAll(regexp, newLine);
        writeTextToFile(file, newFileContent);
    }

    //получаем строковое содержание из файла
    public String getStringFromFile(File file) {
        String tempString = null;
        cache.delete(0, cache.length());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((tempString = reader.readLine()) != null) {
                cache.append(tempString + lineSeparator);
            }
        } catch (IOException e) {
            logger.error("Exception read file " + file.getName() + " " + e.getMessage());
        }
        return cache.toString();
    }

    //получаем строковое содержание из файла по относительному пути файла
    public String getStringFromFile(String path) {
        return getStringFromFile(getFileFromResources(path));
    }


    //записываем текст в файл, точнее перезаписываем файл с новым содержимым
    public void writeTextToFile(File file, String text) {
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            logger.error("Exception write data in file " + file.getName() + " " + ex.getMessage());
        }
    }
}

