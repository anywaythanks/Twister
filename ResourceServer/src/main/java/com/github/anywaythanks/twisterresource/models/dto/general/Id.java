package com.github.anywaythanks.twisterresource.models.dto.general;

import jakarta.validation.constraints.NotNull;

interface Id {
    @NotNull
    Long getId();
}
