package com.github.anywaythanks.twisterresource.models.dto.acase;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

interface VisibleName {
    @NotBlank
    @Length(min = 1, max = 64)
    String getVisibleName();
}
