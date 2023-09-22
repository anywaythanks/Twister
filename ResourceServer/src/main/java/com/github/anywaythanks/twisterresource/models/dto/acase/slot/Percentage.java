package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public interface Percentage {
    @Min(0)
    @Max(100)
    @JsonProperty("win_rate")
    BigDecimal getWinRate();
}
