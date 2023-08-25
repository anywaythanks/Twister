package com.github.anywaythanks.twisterresource.models.dto.account;

import jakarta.validation.constraints.NotNull;

interface NameType<T> {
    @NotNull
    T getType();
}
