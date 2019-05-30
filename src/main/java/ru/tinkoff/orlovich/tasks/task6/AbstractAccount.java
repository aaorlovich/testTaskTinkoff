package ru.tinkoff.orlovich.tasks.task6;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAccount implements Product {
    protected Logger logger = LoggerFactory.getLogger(AbstractAccount.class);

    @Getter
    protected String description;
    protected Long balance;
    @Getter
    @Setter
    protected String currency;

    public AbstractAccount(String description, Long balance, String currency) {
        this.description = description;
        this.balance = balance;
        this.currency = currency;
    }

    @Override
    public Long getBalance() {
        return balance;
    }

    @Override
    public Boolean raiseBalance(Long amount) {
        this.balance = balance + amount;
        //transaction imitation
        return true;
    }
}
