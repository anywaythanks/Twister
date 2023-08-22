package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.MoneyNotTypeExceptions;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;

@Embeddable

public class Money {
    @NotNull
    BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "money_type_id", nullable = false)
    MoneyType moneyType;

    protected Money() {
    }

    public Money(BigDecimal value, MoneyType moneyType) {
        this.value = value;
        this.moneyType = moneyType;
    }

    public Money toZero() {
        return new Money(BigDecimal.ZERO, moneyType);
    }

    public Money add(Money money) {
        if (!money.getTypeMoney().equals(moneyType))
            throw new MoneyNotTypeExceptions();
        return new Money(value.add(money.getValue()), moneyType);
    }

    public Money multiply(BigDecimal value) {
        return new Money(value.multiply(value), moneyType);
    }

    public Money subtract(Money money) {
        if (!money.getTypeMoney().equals(moneyType))
            throw new MoneyNotTypeExceptions();
        return new Money(value.subtract(money.getValue()), moneyType);
    }

    public BigDecimal getValue() {
        return value;
    }

    public MoneyType getTypeMoney() {
        return moneyType;
    }
}
