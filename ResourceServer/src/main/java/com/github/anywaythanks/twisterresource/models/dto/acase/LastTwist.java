package com.github.anywaythanks.twisterresource.models.dto.acase;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

interface LastTwist {
    @NotNull
    Instant getLastTwist();
}
