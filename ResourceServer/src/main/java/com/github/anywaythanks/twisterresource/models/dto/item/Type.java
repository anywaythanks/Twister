package com.github.anywaythanks.twisterresource.models.dto.item;

import jakarta.validation.constraints.NotNull;

interface Type {
    @NotNull
    ItemTypes getType();
}
