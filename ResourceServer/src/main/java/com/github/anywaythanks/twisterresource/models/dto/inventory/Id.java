package com.github.anywaythanks.twisterresource.models.dto.inventory;

import jakarta.validation.constraints.NotBlank;

interface Id {
    @NotBlank
    Long getId();
}
