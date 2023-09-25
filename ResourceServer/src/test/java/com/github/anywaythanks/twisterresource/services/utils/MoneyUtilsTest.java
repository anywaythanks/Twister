package com.github.anywaythanks.twisterresource.services.utils;

import com.github.anywaythanks.twisterresource.exceptions.InvalidMoneyTypeException;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyUtilsTest {
    MoneyType type1;
    MoneyType type2;
    MoneyUtils moneyUtils;

    @BeforeEach
    void initTypes() {
        type1 = MoneyType.builder().name("1").build();
        type2 = MoneyType.builder().name("2").build();
        moneyUtils = new MoneyUtils();
    }

    @Nested
    class Add {
        @Test
        void testOneType() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.ZERO).build();
            moneyUtils.add(money1, money1);
        }

        @Test
        void testTwoType() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.ZERO).build();
            Money money2 = Money.builder().moneyType(type2).value(BigDecimal.ZERO).build();
            InvalidMoneyTypeException exception = assertThrows(InvalidMoneyTypeException.class,
                    () -> moneyUtils.add(money1, money2));
            assertEquals(exception.getMessage(), "Invalid type specified.");
        }

        @Test
        void testAdd() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(13)).build();
            Money money2 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(7)).build();
            Money moneyResult = moneyUtils.add(money1, money2);
            assertEquals(moneyResult.getValue(), BigDecimal.valueOf(20));
            assertEquals(money1.getValue(), BigDecimal.valueOf(13));
            assertEquals(money2.getValue(), BigDecimal.valueOf(7));
        }
    }

    @Nested
    class Multiply {
        @Test
        void testMultiply() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(13)).build();
            Money moneyResult = moneyUtils.multiply(money1,BigDecimal.valueOf(7));
            assertEquals(moneyResult.getValue(), BigDecimal.valueOf(91));
            assertEquals(money1.getValue(), BigDecimal.valueOf(13));
        }
    }

    @Nested
    class Subtract {
        @Test
        void testOneType() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.ZERO).build();
            moneyUtils.subtract(money1, money1);
        }

        @Test
        void testTwoType() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.ZERO).build();
            Money money2 = Money.builder().moneyType(type2).value(BigDecimal.ZERO).build();
            InvalidMoneyTypeException exception = assertThrows(InvalidMoneyTypeException.class,
                    () -> moneyUtils.subtract(money1, money2));
            assertEquals(exception.getMessage(), "Invalid type specified.");
        }

        @Test
        void testSubtract() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(13)).build();
            Money money2 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(7)).build();
            Money moneyResult = moneyUtils.subtract(money1, money2);
            assertEquals(moneyResult.getValue(), BigDecimal.valueOf(6));
            assertEquals(money1.getValue(), BigDecimal.valueOf(13));
            assertEquals(money2.getValue(), BigDecimal.valueOf(7));
        }
    }
}