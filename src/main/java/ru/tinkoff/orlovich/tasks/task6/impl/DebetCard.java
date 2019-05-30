package ru.tinkoff.orlovich.tasks.task6.impl;


/**
 * Еще один законченный продукт.
 * Есть своя реализация, но полиморфизм нам для того и создан
 */
public class DebetCard extends AbstractCard {

    public DebetCard(String description, Long balance, String currency) {
        super(description, balance, currency);
    }

    @Override
    public Boolean debitBalance(Long amount) {
        long temp = balance - amount;
        if (temp < 0) {
            logger.error("Недостаточно средств на счете " + description);
            return false;
        } else {
            balance = temp;
            return true;
        }
    }
}
