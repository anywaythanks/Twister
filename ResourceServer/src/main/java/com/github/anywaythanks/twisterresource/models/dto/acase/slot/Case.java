package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import jakarta.validation.constraints.NotNull;

public interface Case<T> {
    @NotNull
    T getOwnerCase();
}
