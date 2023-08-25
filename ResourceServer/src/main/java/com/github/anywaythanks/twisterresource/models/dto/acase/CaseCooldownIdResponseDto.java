package com.github.anywaythanks.twisterresource.models.dto.acase;

import java.time.Duration;

public class CaseCooldownIdResponseDto implements Id, Cooldown {
    Long id;
    Duration cooldown;

    protected CaseCooldownIdResponseDto() {

    }

    public CaseCooldownIdResponseDto(Long id, Duration cooldown) {
        this.id = id;
        this.cooldown = cooldown;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Duration getCooldown() {
        return cooldown;
    }


    public void setCooldown(Duration cooldown) {
        this.cooldown = cooldown;
    }
}
