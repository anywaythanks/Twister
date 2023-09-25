package com.github.anywaythanks.twisterresource.services.utils;

import com.github.anywaythanks.twisterresource.exceptions.InvalidMoneyTypeException;
import com.github.anywaythanks.twisterresource.models.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoneyUtils {
    public Money multiply(Money money, BigDecimal value) {
        return Money.builder()
                .value(money.getValue().multiply(value))
                .moneyType(money.getMoneyType())
                .build();
    }

    public Money add(Money m1, Money m2) {
        if (!m1.getMoneyType().equals(m2.getMoneyType()))
            throw new InvalidMoneyTypeException();
        return Money.builder()
                .value(m1.getValue().add(m2.getValue()))
                .moneyType(m1.getMoneyType())
                .build();
    }
    public Money subtract(Money minuend, Money subtrahend) {
        if (!minuend.getMoneyType().equals(subtrahend.getMoneyType()))
            throw new InvalidMoneyTypeException();
        return Money.builder()
                .value(minuend.getValue().subtract(subtrahend.getValue()))
                .moneyType(minuend.getMoneyType())
                .build();
    }
}
