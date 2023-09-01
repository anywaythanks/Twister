package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class MoneyFullDto implements Value, GetterType<MoneyTypeFullDto> {
    @NonNull
    MoneyTypeFullDto type;
    @NonNull
    BigDecimal value;
}
