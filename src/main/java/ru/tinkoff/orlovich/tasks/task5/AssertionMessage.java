package ru.tinkoff.orlovich.tasks.task5;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssertionMessage {
    private String message;
    private boolean isError;
}
