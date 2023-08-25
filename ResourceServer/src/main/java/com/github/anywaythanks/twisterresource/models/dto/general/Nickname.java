package com.github.anywaythanks.twisterresource.models.dto.general;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

interface Nickname {
    @NotBlank
    @Length(min = 3, max = 64)
    String getNickname();
}
