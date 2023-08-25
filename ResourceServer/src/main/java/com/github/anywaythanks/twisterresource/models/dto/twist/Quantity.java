package com.github.anywaythanks.twisterresource.models.dto.twist;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

interface Quantity {
    @NotNull
    @PositiveOrZero
    Integer getQuantity();
}
