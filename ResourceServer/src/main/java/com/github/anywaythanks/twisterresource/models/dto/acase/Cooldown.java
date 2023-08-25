package com.github.anywaythanks.twisterresource.models.dto.acase;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

interface Cooldown {
    @NotNull
    Duration getCooldown();
}
