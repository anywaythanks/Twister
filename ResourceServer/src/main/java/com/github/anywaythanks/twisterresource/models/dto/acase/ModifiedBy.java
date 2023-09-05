package com.github.anywaythanks.twisterresource.models.dto.acase;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public interface ModifiedBy {
    @NotNull
    Instant getModifiedBy();
}
