package com.github.anywaythanks.twisterresource.models.dto.money.type;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class MoneyTypePartialResponseDto implements Name, PathToIcon {
    @NonNull
    String name;
    @NonNull
    String pathToIcon;
}
