package com.github.anywaythanks.twisterresource.models.dto.general;

import jakarta.validation.constraints.NotBlank;

interface Uuid {
    @NotBlank
    String getUuid();
}
