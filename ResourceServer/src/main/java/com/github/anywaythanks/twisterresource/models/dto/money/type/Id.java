package com.github.anywaythanks.twisterresource.models.dto.money.type;

import jakarta.validation.constraints.NotNull;

interface Id {

    @NotNull
    Integer getId();
}
