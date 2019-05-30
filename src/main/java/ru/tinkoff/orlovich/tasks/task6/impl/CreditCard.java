package ru.tinkoff.orlovich.tasks.task6.impl;

import lombok.Getter;

public class CreditCard extends AbstractCard {

    @Getter
    private Double interestRate;


    public CreditCard(String description, Long balance, String currency, Double interestRate) {
        super(description, balance, currency);
        this.interestRate = interestRate;
    }

    @Override
    public Boolean debitBalance(Long amount) {
        balance = balance - amount;
        return true;
    }

    public Long getDebt() {
        if (balance < 0) {
            return balance;
        }
        return 0L;
    }

}
