package com.github.anywaythanks.twisterresource.models.dto.twistMark;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public interface ModifiedBy {
    @NotNull
    Instant getModifiedBy();
}
