package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class MoneyFullDto implements Value, GetterType<MoneyTypeFullDto> {
    @NonNull MoneyTypeFullDto type;
    @NonNull BigDecimal value;
}
