package com.github.anywaythanks.twisterresource.models.dto.general;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public interface CreatedOn {
    @NotNull
    Instant getCreatedOn();
}
