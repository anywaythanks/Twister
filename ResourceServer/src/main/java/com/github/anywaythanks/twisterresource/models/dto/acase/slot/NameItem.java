package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import jakarta.validation.constraints.NotNull;

public interface NameItem<T> {
    @NotNull
    T getItem();
}
