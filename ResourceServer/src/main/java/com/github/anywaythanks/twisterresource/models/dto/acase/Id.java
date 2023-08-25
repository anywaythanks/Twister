package com.github.anywaythanks.twisterresource.models.dto.acase;

import jakarta.validation.constraints.NotNull;

interface Id {
    @NotNull
    Long getId();
}
