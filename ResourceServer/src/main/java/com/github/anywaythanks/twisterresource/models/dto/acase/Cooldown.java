package com.github.anywaythanks.twisterresource.models.dto.acase;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.time.Duration;

interface Cooldown {
    @NotNull
    @NonNull
    Duration getCooldown();
}
