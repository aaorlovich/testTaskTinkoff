package ru.tinkoff.orlovich.tasks.task6.impl;

import ru.tinkoff.orlovich.tasks.task6.AbstractAccount;


/**
 * Более узкий тип чем аккаунт, выступает узлом для карт и содержит еще одтн специфичный метод уменьшения счета
 * <p>
 * позволит расширять различные виды карт, допусти акционная или партнерская
 * <p>
 * Полиморфизм в любом случае можно задействовать в любом слое если специфика продукта нам не важна
 */
public abstract class AbstractCard extends AbstractAccount {

    public AbstractCard(String description, Long balance, String currency) {
        super(description, balance, currency);
    }

    public abstract Boolean debitBalance(Long amount);

}
