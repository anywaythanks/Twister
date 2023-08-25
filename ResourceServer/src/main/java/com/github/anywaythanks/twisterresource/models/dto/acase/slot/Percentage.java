package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public interface Percentage {
    @Min(0)
    @Max(1)
    BigDecimal getPercentage();
}
