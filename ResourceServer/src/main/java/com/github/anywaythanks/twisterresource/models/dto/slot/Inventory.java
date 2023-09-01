package com.github.anywaythanks.twisterresource.models.dto.slot;

import jakarta.validation.constraints.NotNull;

interface Inventory<T> {
    @NotNull
    T getInventory();
}
