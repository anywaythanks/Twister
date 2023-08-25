package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

interface Name {
    @NotBlank
    @Length(min = 1, max = 64)
    String getName();
}
