package com.github.anywaythanks.twisterresource.models.dto.twist;

import jakarta.validation.constraints.NotNull;

interface GeneralAccount<T> {
    @NotNull
    T getGeneralAccount();
}
