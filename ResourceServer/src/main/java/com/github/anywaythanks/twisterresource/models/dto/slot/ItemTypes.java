package com.github.anywaythanks.twisterresource.models.dto.slot;

import jakarta.validation.constraints.NotNull;

interface ItemTypes<T> {
    @NotNull
    T getItem();
}
