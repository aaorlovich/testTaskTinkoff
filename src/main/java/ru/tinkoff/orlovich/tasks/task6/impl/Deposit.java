package ru.tinkoff.orlovich.tasks.task6.impl;

import ru.tinkoff.orlovich.tasks.task6.AbstractAccount;


/**
 * Вклад высттупает в качестве самостоятельного конечного продукта, но имеет специфику которая должна учитсявать в более
 * низких слоях приложения, и для более конкретных целей придется использовать обхект именно этого типа
 */

public class Deposit extends AbstractAccount {

    public Deposit(String description, Long balance, String currency) {
        super(description, balance, currency);
    }

    public void closeDeposit() {
        logger.info("Close deposit!!!");
    }

}
