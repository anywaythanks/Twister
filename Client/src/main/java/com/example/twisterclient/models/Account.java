package com.example.twisterclient.models;

public class Account {
    Money amount;
    String number;

    protected Account() {
    }

    public Account(Money amount, String number) {
        this.amount = amount;
        this.number = number;
    }

    public Money getAmount() {
        return amount;
    }

    public String getNumber() {
        return number;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
