package com.github.anywaythanks.twisterresource.models.dto.twistMark;

import jakarta.validation.constraints.NotNull;

interface Id {
    @NotNull
    Long getId();
}
