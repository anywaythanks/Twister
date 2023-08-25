package com.github.anywaythanks.twisterresource.models.dto.money;

import jakarta.validation.constraints.NotNull;

interface NameType<T> {
    @NotNull
    T getType();
}
