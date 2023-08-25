package com.github.anywaythanks.twisterresource.models.dto.account;

import jakarta.validation.constraints.NotNull;

import java.util.List;

interface Slots<T> {
    @NotNull
    List<T> getSlots();
}
