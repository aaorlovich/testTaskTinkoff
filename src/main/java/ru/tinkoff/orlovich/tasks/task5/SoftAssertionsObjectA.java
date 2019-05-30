package ru.tinkoff.orlovich.tasks.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.orlovich.tasks.task5.model.ObjectA;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class SoftAssertionsObjectA {
    Logger logger = LoggerFactory.getLogger(SoftAssertionsObjectA.class);

    /**
     * Имеется класс ObjectA, содержащий следующие поля: int id (уникально, т.е. не изменно), String field,
     * String fieldValue. Даны 2 списка таких объектов по 4 объекта в каждом. Т.к. id уникально, то в
     * каждом списке будет объект с id = 1, другой с id = 2, далее id = 3 и id = 4. То есть для каждого
     * объекта есть пара в другом списке.
     * Нужно сравнить попарно объекты из двух списков (первый список – эталонный, второй из внешнего
     * источника). Задача не про реализацию equals, оптимально сделать реализацию soft assert. Вывод –
     * стандартный вывод.
     */


    public static void main(String[] args) {

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

    public void assertThat(List<ObjectA> reference, List<ObjectA> verifiable) {
        List<AssertionMessage> messages = new ArrayList<>();
        reference.stream().forEach(ref -> {
            Stream<ObjectA> objectAStream = verifiable.stream().filter(v -> ref.getId() == v.getId());
            ObjectA verifiableObject = objectAStream.findFirst().get();
            if (verifiableObject != null) {
                messages.add(assertObjects(ref, verifiableObject));
            } else {
                messages.add(new AssertionMessage("Для объекта с id " + ref.getId() + " соответсвующего объекта не нашлось\n", true));
            }
        });

        printOkOrThrowAssertionError(messages);

    }

    private void printOkOrThrowAssertionError(List<AssertionMessage> messages) {
        if (messages == null || messages.isEmpty()) {
            return;
        }
        StringBuilder messageBuilder = new StringBuilder();
        AtomicBoolean isError = new AtomicBoolean(false);
        messages.stream().forEach(s -> {
            messageBuilder.append(s.getMessage());
            if (s.isError()) {
                isError.set(true);
            }
        });
        if (isError.get()) {
            throw new AssertionError(messageBuilder);
        } else {
            logger.info(messageBuilder.toString());
        }
    }

    private AssertionMessage assertObjects(ObjectA reference, ObjectA verifiable) {
        StringBuilder message = new StringBuilder();
        String withStartMessage = String.format("Для id %s ", reference.getId());
        String formatErrorMessage = "не совпало следующее: Поле %s => ожидаемое значение %s, текущее %s. \n";
        if (reference.equals(verifiable)) {
            message.append(withStartMessage + "все ОК!\n");
            return new AssertionMessage(message.toString(), false);
        }

        String referenceField = reference.getField();
        String verifiableField = verifiable.getField();
        if (referenceField != null && verifiableField != null && !referenceField.equals(verifiableField)) {
            message.append(withStartMessage + String.format(formatErrorMessage, "field", referenceField, verifiableField));
        }

        String referenceFieldValue = reference.getFieldValue();
        String verifiableFieldValue = verifiable.getFieldValue();
        if (referenceFieldValue != null && verifiableFieldValue != null && !referenceFieldValue.equals(verifiableFieldValue)) {
            message.append(withStartMessage + String.format(formatErrorMessage, "fieldValue", referenceFieldValue, verifiableFieldValue));
        }
        return new AssertionMessage(message.toString(), true);
    }
}
