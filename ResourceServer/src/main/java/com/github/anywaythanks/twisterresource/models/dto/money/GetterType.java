package com.github.anywaythanks.twisterresource.models.dto.money;

import jakarta.validation.constraints.NotNull;

interface GetterType<T> {
    @NotNull
    T getType();
}
