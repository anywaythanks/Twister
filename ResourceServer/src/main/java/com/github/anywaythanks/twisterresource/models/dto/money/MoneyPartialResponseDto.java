package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class MoneyPartialResponseDto implements Value, GetterType<MoneyTypePartialResponseDto> {
    @NonNull
    MoneyTypePartialResponseDto type;
    @NonNull
    BigDecimal value;
}
