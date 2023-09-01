package com.github.anywaythanks.twisterresource.models.dto.money.type;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class MoneyTypeIdResponseDto implements Id {
    @NonNull
    Integer id;
}
