package com.github.anywaythanks.twisterresource.models.dto.acase;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

interface Description {
    @NotNull
    @Length(max = 1000)
    String getDescription();
}
