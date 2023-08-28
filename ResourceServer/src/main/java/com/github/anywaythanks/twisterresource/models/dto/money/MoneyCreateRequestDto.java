package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class MoneyCreateRequestDto implements Value, NameType<MoneyTypeNameRequestDto> {
    @NonNull
    MoneyTypeNameRequestDto type;
    @NonNull
    BigDecimal value;
}
