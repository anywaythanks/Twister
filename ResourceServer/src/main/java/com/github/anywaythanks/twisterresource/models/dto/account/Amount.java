package com.github.anywaythanks.twisterresource.models.dto.account;

import jakarta.validation.constraints.NotNull;

interface Amount<T> {
    @NotNull
    T getAmount();
}
