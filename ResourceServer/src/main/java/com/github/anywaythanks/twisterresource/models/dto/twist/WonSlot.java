package com.github.anywaythanks.twisterresource.models.dto.twist;

import jakarta.validation.constraints.NotNull;

public interface WonSlot<T> {
    @NotNull
    T getSlot();
}
