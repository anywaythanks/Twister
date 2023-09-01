package com.github.anywaythanks.twisterresource.models.dto.money.type;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public interface CreatedOn {
    @NotNull
    Instant getCreatedOn();
}
