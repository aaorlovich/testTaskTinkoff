package ru.tinkoff.orlovich.tasks.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.orlovich.tasks.task5.model.ObjectA;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;


/**
 * @author Orlovich Artem
 * <p>
 * Задача 5
 * Задача про сравнение двух объектов.
 * Имеется класс ObjectA, содержащий следующие поля: int id (уникально, т.е. не изменно), String field,
 * String fieldValue. Даны 2 списка таких объектов по 4 объекта в каждом. Т.к. id уникально, то в
 * каждом списке будет объект с id = 1, другой с id = 2, далее id = 3 и id = 4. То есть для каждого
 * объекта есть пара в другом списке.
 * Нужно сравнить попарно объекты из двух списков (первый список – эталонный, второй из внешнего
 * источника). Задача не про реализацию equals, оптимально сделать реализацию soft assert. Вывод –
 * стандартный вывод.
 * Входные данные для тестирования:
 * List<ObjectA> referenceData = new ArrayList<>();
 * <p>
 * referenceData.add(new ObjectA(1, "field1", "value1"));
 * referenceData.add(new ObjectA(2, "field2", "value2"));
 * referenceData.add(new ObjectA(3, "field3", "value3"));
 * referenceData.add(new ObjectA(4, "field4", "value4"));
 * <p>
 * <p>
 * List<ObjectA> verifiableData = new ArrayList<>();
 * <p>
 * verifiableData.add(new ObjectA(1, "field1", "value1"));
 * verifiableData.add(new ObjectA(2, "otherField", "value2"));
 * verifiableData.add(new ObjectA(3, "field3", "otherValue"));
 * verifiableData.add(new ObjectA(4, "otherField", "otherValue"));
 * <p>
 * В результате работы вашей функции в стандартный вывод вы должны вывести следующее:
 * Для id 1 все ОК!
 * Для id 2 не совпало следующее: Поле field =&gt; ожидаемое значение field2, текущее otherField
 * Для id 3 не совпало следующее: Поле fieldValue =&gt; ожидаемое значение value3, текущее otherValue
 * Для id 4 не совпало следующее: Поле field =&gt; ожидаемое значение field4, текущее otherField. Для id
 * 4 не совпало следующее: Поле fieldValue =&gt; ожидаемое значение value4, текущее otherValue.
 * <p>
 * <p>
 * <p>
 * Апи класса предоставляет только один публичный метод, assertThat()
 * которому на вход подается две коллекции(возможно не упорядоченных), они сравниваються и формируется
 * исходное сообщение о проверке,
 * если коллекции не идентичны сгенерируется AssertionError
 * иначе по всем объектам выведется в лог что все ОК
 */
public class SoftAssertionsObjectA {
    //logger
    Logger logger = LoggerFactory.getLogger(SoftAssertionsObjectA.class);

//    public static void main(String[] args) {
//        List<ObjectA> referenceData = new ArrayList<>();
//        referenceData.add(new ObjectA(1, "field1", "value1"));
//        referenceData.add(new ObjectA(2, "field2", "value2"));
//        referenceData.add(new ObjectA(3, "field3", "value3"));
//        referenceData.add(new ObjectA(4, "field4", "value4"));
//        List<ObjectA> verifiableData = new ArrayList<>();
//        verifiableData.add(new ObjectA(1, "field1", "value1"));
//        verifiableData.add(new ObjectA(2, "otherField", "value2"));
//        verifiableData.add(new ObjectA(3, "field3", "otherValue"));
//        verifiableData.add(new ObjectA(4, "otherField", "otherValue"));
//
//        SoftAssertionsObjectA assertions = new SoftAssertionsObjectA();
//        assertions.assertThat(referenceData, verifiableData);
//    }


    /*
     * Основной метод сравнения объектов класса ObjectA
     * Делать что то универсальное не было времени, и рефлексию давно не испольщовал чтобы доставать поля и прочее
     * но работает согласно заданию, медленно, за счет возможности сравнивать не упорядоченные коллекции,
     * но не настолько медленно чтобы считать метод провальным
     *
     * по капотом все просто
     * сначала фильтруем значения второй коллекции и находим пару для значения первой по id
     * сравниваем пару объектов и формируем сообщение о проверки методом assertObjects
     * добавляем каждый раз в лист полученные сообщения о преверки(составной объект сообщения AssertionMessage )
     * проверяем лист сообщений и решаем выкидывать ли Error с ошибками либо просто печатаем в лог и считаем что тест
     * успешен
     *
     * */
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


    /*
    * Метод получает на вход коллекцию сообщений об ошибках
     * если хотя бы одно сообщение сигнализирует о том что проверка была не успешна то
     * выкиывает AssertionError со всеми сообщениями в коллекции
     * иначе просто печатает в консоль о реультате проверки(должна быть полностью ок)
     *
     * */
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


    /*Метод сравнивает 2 объекта типа ObjectA и возврашает результат сравнения в виде
     * AssertionMessage
     * */
    public AssertionMessage assertObjects(ObjectA reference, ObjectA verifiable) {
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
