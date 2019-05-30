package ru.tinkoff.orlovich.tasks.task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static ru.tinkoff.orlovich.commons.utils.Helper.getFileFromResources;

public class TxtFileFormatter {

    private final File file;
    Logger logger = LoggerFactory.getLogger(TxtFileFormatter.class);

    private String delimiter = ";";
    private String lineSeparator = System.lineSeparator();
    private StringBuilder cache = new StringBuilder();


    public TxtFileFormatter(File file) {
        this.file = file;
    }

    public TxtFileFormatter(String filePath) {
        this.file = getFileFromResources(filePath);
    }

    public TxtFileFormatter(File file, String delimiter) {
        this.file = file;
        this.delimiter = delimiter;
    }

    public TxtFileFormatter(File file, String delimiter, String lineSeparator) {
        this.file = file;
        this.delimiter = delimiter;
        this.lineSeparator = lineSeparator;
    }

    public static void main(String[] args) {
        TxtFileFormatter formatter = new TxtFileFormatter("testData/task3.txt");
        formatter.replaceValueOfColumn("key1", "newValue");
    }

    public void replaceValueOfColumn(String columnName, String newValue) {
        String oldFileContent = getStringFromFile(file);
        String regexp = columnName + delimiter + ".*\\s";
        String newLine = columnName + delimiter + newValue;
        String newFileContent = oldFileContent.replaceAll(regexp, newLine);
        writeTextToFile(file, newFileContent);
    }


    public String getStringFromFile(File file) {
        String tempString = null;
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


    public String getStringFromFile(String path) {
        return getStringFromFile(getFileFromResources(path));
    }

    public void writeTextToFile(File file, String text) {
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            logger.error("Exception write data in file " + file.getName() + " " + ex.getMessage());
        }
    }
}

