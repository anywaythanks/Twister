package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.InvalidMoneyTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {
    MoneyType type1;
    MoneyType type2;

    @BeforeEach
    void initTypes() {
        type1 = MoneyType.builder().name("1").build();
        type2 = MoneyType.builder().name("2").build();
    }

    @Nested
    class Add {
        @Test
        void testOneType() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.ZERO).build();
            money1.add(money1);
        }

        @Test
        void testTwoType() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.ZERO).build();
            Money money2 = Money.builder().moneyType(type2).value(BigDecimal.ZERO).build();
            InvalidMoneyTypeException exception = assertThrows(InvalidMoneyTypeException.class,
                    () -> money1.add(money2));
            assertEquals(exception.getMessage(), "Invalid type specified.");
        }

        @Test
        void testAdd() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(13)).build();
            Money money2 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(7)).build();
            Money moneyResult = money1.add(money2);
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
            Money moneyResult = money1.multiply(BigDecimal.valueOf(7));
            assertEquals(moneyResult.getValue(), BigDecimal.valueOf(91));
            assertEquals(money1.getValue(), BigDecimal.valueOf(13));
        }
    }

    @Nested
    class Subtract {
        @Test
        void testOneType() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.ZERO).build();
            money1.subtract(money1);
        }

        @Test
        void testTwoType() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.ZERO).build();
            Money money2 = Money.builder().moneyType(type2).value(BigDecimal.ZERO).build();
            InvalidMoneyTypeException exception = assertThrows(InvalidMoneyTypeException.class,
                    () -> money1.subtract(money2));
            assertEquals(exception.getMessage(), "Invalid type specified.");
        }

        @Test
        void testSubtract() {
            Money money1 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(13)).build();
            Money money2 = Money.builder().moneyType(type1).value(BigDecimal.valueOf(7)).build();
            Money moneyResult = money1.subtract(money2);
            assertEquals(moneyResult.getValue(), BigDecimal.valueOf(6));
            assertEquals(money1.getValue(), BigDecimal.valueOf(13));
            assertEquals(money2.getValue(), BigDecimal.valueOf(7));
        }
    }
}