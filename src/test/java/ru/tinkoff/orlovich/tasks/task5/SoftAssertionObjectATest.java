package ru.tinkoff.orlovich.tasks.task5;

import org.testng.annotations.Test;
import ru.tinkoff.orlovich.tasks.task5.model.ObjectA;

import java.util.ArrayList;
import java.util.List;

public class SoftAssertionObjectATest {

    private SoftAssertionsObjectA softAssertions = new SoftAssertionsObjectA();

    /**
     * Метод как и пологается для Assertion кидает AssertionError и выводит в лог все согласно заданию
     * ---- start test: assertThatTest
     * --- desc:
     * ---- test: assertThatTest FAIL, because exception: Для id 1 все ОК!
     * Для id 2 не совпало следующее: Поле field => ожидаемое значение field2, текущее otherField.
     * Для id 3 не совпало следующее: Поле fieldValue => ожидаемое значение value3, текущее otherValue.
     * Для id 4 не совпало следующее: Поле field => ожидаемое значение field4, текущее otherField.
     * Для id 4 не совпало следующее: Поле fieldValue => ожидаемое значение value4, текущее otherValue.
     */

    @Test(description = "TASK 5", expectedExceptions = AssertionError.class)
    public void assertThatTest() {
        List<ObjectA> referenceData = new ArrayList<>();
        referenceData.add(new ObjectA(1, "field1", "value1"));
        referenceData.add(new ObjectA(2, "field2", "value2"));
        referenceData.add(new ObjectA(3, "field3", "value3"));
        referenceData.add(new ObjectA(4, "field4", "value4"));
        List<ObjectA> verifiableData = new ArrayList<>();
        verifiableData.add(new ObjectA(1, "field1", "value1"));
        verifiableData.add(new ObjectA(2, "otherField", "value2"));
        verifiableData.add(new ObjectA(3, "field3", "otherValue"));
        verifiableData.add(new ObjectA(4, "otherField", "otherValue"));

        SoftAssertionsObjectA assertions = new SoftAssertionsObjectA();
        assertions.assertThat(referenceData, verifiableData);
    }
}
