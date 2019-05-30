package ru.tinkoff.orlovich.tasks.task6.impl;

import ru.tinkoff.orlovich.tasks.task6.AbstractAccount;

public class Deposit extends AbstractAccount {

    public Deposit(String description, Long balance, String currency) {
        super(description, balance, currency);
    }

    public void closeDeposit() {
        logger.info("Close deposit!!!");
    }

}
