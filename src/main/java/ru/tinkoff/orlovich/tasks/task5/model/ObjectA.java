package ru.tinkoff.orlovich.tasks.task5.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * Обычный Pojo объект
 */
@EqualsAndHashCode
public class ObjectA {
    @Getter
    private final int id;

    @Getter
    @Setter
    private String field;

    @Getter
    @Setter
    private String fieldValue;

    public ObjectA(int id, String field, String fieldValue) {
        this.id = id;
        this.field = field;
        this.fieldValue = fieldValue;
    }


}
