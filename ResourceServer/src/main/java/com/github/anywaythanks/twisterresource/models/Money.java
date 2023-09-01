package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.MoneyNotTypeExceptions;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class Money {
    @NotNull
    @NonNull
    BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "money_type_id", nullable = false, updatable = false, insertable = false)
    @NonNull
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
