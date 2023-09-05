package com.github.anywaythanks.twisterresource.models.dto.twist;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

interface Number {
    @NotBlank
    @Length(min = 1, max = 64)
    String getNumber();
}
