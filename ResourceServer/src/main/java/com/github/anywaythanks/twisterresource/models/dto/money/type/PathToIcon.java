package com.github.anywaythanks.twisterresource.models.dto.money.type;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

interface PathToIcon {
    @NotBlank
    @Length(min = 1, max = 64)
    String getPathToIcon();
}
