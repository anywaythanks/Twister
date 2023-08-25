package com.github.anywaythanks.twisterresource.models.dto.page;

import jakarta.validation.constraints.NotNull;

import java.util.List;

interface Values<T> {
    @NotNull
    List<T> getValues();
}
