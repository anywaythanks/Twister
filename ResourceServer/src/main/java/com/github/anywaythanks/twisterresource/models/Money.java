package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.InvalidMoneyTypeException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
            throw new InvalidMoneyTypeException();
        return new Money(value.add(money.getValue()), moneyType);
    }

    public Money multiply(BigDecimal value) {
        return new Money(this.value.multiply(value), moneyType);
    }

    public Money subtract(Money money) {
        if (!money.getMoneyType().equals(moneyType))
            throw new InvalidMoneyTypeException();
        return new Money(value.subtract(money.getValue()), moneyType);
    }
}
