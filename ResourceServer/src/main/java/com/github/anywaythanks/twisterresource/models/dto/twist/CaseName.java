package com.github.anywaythanks.twisterresource.models.dto.twist;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

interface CaseName {
    @NotBlank
    @Length(max = 64)
    String getCaseName();
}
