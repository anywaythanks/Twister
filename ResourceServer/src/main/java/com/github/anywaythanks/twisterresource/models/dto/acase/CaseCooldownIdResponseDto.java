package com.github.anywaythanks.twisterresource.models.dto.acase;

import lombok.*;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseCooldownIdResponseDto implements Id, Cooldown {
    @NonNull
    Long id;

    @NonNull
    Duration cooldown;

    public CaseCooldownIdResponseDto setCooldown(Duration cooldown) {
        return new CaseCooldownIdResponseDto(id, cooldown);
    }
}