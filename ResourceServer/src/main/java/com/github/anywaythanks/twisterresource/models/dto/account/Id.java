package com.github.anywaythanks.twisterresource.models.dto.account;

import jakarta.validation.constraints.NotNull;

interface Id {
    @NotNull
    Long getId();
}
