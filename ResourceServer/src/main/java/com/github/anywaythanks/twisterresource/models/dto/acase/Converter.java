package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.acase.slot.Percentage;

import java.math.BigDecimal;
import java.util.function.Function;

class Converter implements Function<Object, BigDecimal> {
    @Override
    public BigDecimal apply(Object o) {
        if (o instanceof Percentage p) {
            return p.getPercentage();
        } else throw new RuntimeException();
    }
}
