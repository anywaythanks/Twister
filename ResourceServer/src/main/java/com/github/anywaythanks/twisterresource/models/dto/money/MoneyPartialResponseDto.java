package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class MoneyPartialResponseDto implements Value, GetterType<MoneyTypePartialResponseDto> {
    @NonNull MoneyTypePartialResponseDto type;
    @NonNull BigDecimal value;
}
