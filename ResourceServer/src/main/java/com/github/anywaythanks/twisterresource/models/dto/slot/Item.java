package com.github.anywaythanks.twisterresource.models.dto.slot;

import jakarta.validation.constraints.NotNull;

interface Item<T> {
    @NotNull
    T getItem();
}
