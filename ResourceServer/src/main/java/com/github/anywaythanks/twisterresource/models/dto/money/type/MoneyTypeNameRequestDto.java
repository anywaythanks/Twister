package com.github.anywaythanks.twisterresource.models.dto.money.type;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class MoneyTypeNameRequestDto implements Name {
    @NonNull
    String name;
}
