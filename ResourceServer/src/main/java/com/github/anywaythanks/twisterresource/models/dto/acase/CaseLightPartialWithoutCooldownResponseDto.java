package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseLightPartialWithoutCooldownResponseDto implements Name,
        Price<MoneyPartialResponseDto>, VisibleName, Description {
    @NonNull
    MoneyPartialResponseDto price;
    @NonNull
    String name;
    @NonNull
    String visibleName;
    @NonNull
    String description;
}
