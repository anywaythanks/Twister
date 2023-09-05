package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.annotation.CreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@CreateRequestDto
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
public class MoneyCreateRequestDto implements Value, NameType<MoneyTypeNameRequestDto> {
    @NonNull MoneyTypeNameRequestDto type;
    @NonNull BigDecimal value;
}
