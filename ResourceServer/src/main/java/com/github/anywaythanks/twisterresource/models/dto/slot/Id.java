package com.github.anywaythanks.twisterresource.models.dto.slot;

import jakarta.validation.constraints.NotNull;

interface Id {
    @NotNull
    Long getId();
}
