package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.*;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseLightPartialResponseDto implements Name, Cooldown,
        Price<MoneyPartialResponseDto>, VisibleName, Description {
    @NonNull
    MoneyPartialResponseDto price;
    @NonNull
    Duration cooldown;
    @NonNull
    String name;
    @NonNull
    String visibleName;
    @NonNull
    String description;

    public CaseLightPartialResponseDto setCooldown(Duration cooldown) {
        return new CaseLightPartialResponseDto(price, cooldown, name, visibleName, description);
    }
}
