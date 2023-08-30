package com.github.anywaythanks.twisterresource.models.dto.twist;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

interface TwistedOn {
    @NotNull
    Instant getTwistedOn();
}
