package ru.tinkoff.orlovich.tasks.task2;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionsComparatorTest {

    private CollectionsComparator collectionsComparator;


    @BeforeClass
    public void setUp() {
        collectionsComparator = new CollectionsComparator();
    }

    @AfterClass
    public void tearDown() {
    }

    String expectedCompare1 = "Лишние объекты которые содержаться во внешнем источнике (нет в эталонном источнике): \n" +
            "E\n" +
            "Объекты, которых нет в списке из внешнего источника, но присутствуют в эталонном списке: \n";
    String expectedCompare2 = "Лишние объекты которые содержаться во внешнем источнике (нет в эталонном источнике): \n" +
            "E\n" +
            "Объекты, которых нет в списке из внешнего источника, но присутствуют в эталонном списке: \n" +
            "D\n";

    @Test(description = "TASK 2: Проверка вывода в консоль результата сравнения двух коллекций")
    public void compareAndPrintDifferentTest() {
        List<String> standard = Arrays.asList("A", "B", "C", "D");
        List<String> outer = Arrays.asList("A", "B", "C", "D", "E");


        List<String> standard2 = Arrays.asList("A", "B", "C", "D");
        List<String> outer2 = Arrays.asList("A", "B", "C", "E");
        CollectionsComparator comparator = new CollectionsComparator();

        String comparing1 = comparator.compareAndPrintDifferent(standard, outer);
        String comparing2 = comparator.compareAndPrintDifferent(standard2, outer2);

        assertThat(comparing1).isEqualTo(expectedCompare1);
        assertThat(comparing2).isEqualTo(expectedCompare2);
    }
}
