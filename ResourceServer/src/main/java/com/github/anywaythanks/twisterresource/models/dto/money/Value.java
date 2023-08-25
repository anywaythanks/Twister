package com.github.anywaythanks.twisterresource.models.dto.money;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

interface Value {
    @NotNull
    @Positive
    BigDecimal getValue();
}
