package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import jakarta.validation.constraints.Min;

public interface Quantity {
    @Min(1)
    Integer getQuantity();
}
