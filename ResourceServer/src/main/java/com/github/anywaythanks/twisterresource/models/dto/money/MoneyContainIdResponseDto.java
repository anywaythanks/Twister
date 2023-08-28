package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class MoneyContainIdResponseDto implements Value, GetterType<MoneyTypeIdResponseDto> {
    @NonNull
    MoneyTypeIdResponseDto type;
    @NonNull
    BigDecimal value;
}
