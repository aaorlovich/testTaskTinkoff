package ru.tinkoff.orlovich.tasks.task5;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Orlovich Artem
 * <p>
 * Специальный класс для хранения сообщения результата сравнения и флага была ли ошибка - для использования проброса Assert в тестах
 * иначе зачем делать Assertion
 * <p>
 */
@Data
@AllArgsConstructor
public class AssertionMessage {
    private String message;
    private boolean isError;
}
