package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.MoneyNotTypeExceptions;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Builder
public class Money {
    @NotNull
    BigDecimal value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "money_type_id", nullable = false)
    MoneyType moneyType;

    public Money add(Money money) {
        if (!money.getMoneyType().equals(moneyType))
            throw new MoneyNotTypeExceptions();
        return new Money(value.add(money.getValue()), moneyType);
    }

    public Money multiply(BigDecimal value) {
        return new Money(value.multiply(value), moneyType);
    }

    public Money subtract(Money money) {
        if (!money.getMoneyType().equals(moneyType))
            throw new MoneyNotTypeExceptions();
        return new Money(value.subtract(money.getValue()), moneyType);
    }
}
