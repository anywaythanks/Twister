package com.github.anywaythanks.twisterresource.models.dto.slot;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

interface Quantity {
    @NotNull
    @PositiveOrZero
    Integer getQuantity();
}
