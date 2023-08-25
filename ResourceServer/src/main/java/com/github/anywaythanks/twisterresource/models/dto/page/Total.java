package com.github.anywaythanks.twisterresource.models.dto.page;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

interface Total {
    @PositiveOrZero
    @NotNull
    Integer getTotalPages();
}
