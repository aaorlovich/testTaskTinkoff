package ru.tinkoff.orlovich.tasks.task3;

import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.tinkoff.orlovich.commons.utils.HelperUtils.getFileFromResources;

public class TxtFileFormatterTest {

    File file = getFileFromResources("testData/newTestTask3.txt");
    TxtFileFormatter fileFormatter = new TxtFileFormatter(file);

    String testTextToWrite = "key1;value1\r\n" +
            "key2;value2\r\n" +
            "key3;value3\r\n";


    @Test(description = "TASK 3: Проверяется успешная запись в файл и заодно метод получения строки из файла")
    public void writeTextToFileTest() {
        fileFormatter.writeTextToFile(file, testTextToWrite);
        String stringFromFile = fileFormatter.getStringFromFile(file);
        assertThat(stringFromFile).isEqualTo(testTextToWrite);
    }

    String expectedReplasementText = "key1;newValue1\r\n" +
            "key2;value2\r\n" +
            "key3;value3\r\n";

    @Test(description = "TASK 3: Проверка изменения значения колонки", dependsOnMethods = "writeTextToFileTest")
    public void replaceValueOfColumnTest() {
        fileFormatter.replaceValueOfColumn("key1", "newValue1");
        String stringFromFile = fileFormatter.getStringFromFile(file);
        assertThat(stringFromFile).isEqualTo(expectedReplasementText);
    }
}
