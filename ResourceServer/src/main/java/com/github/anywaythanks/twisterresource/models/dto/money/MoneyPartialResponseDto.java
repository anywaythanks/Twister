package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;

import java.math.BigDecimal;

public class MoneyPartialResponseDto implements Value, GetterType<MoneyTypePartialResponseDto> {
    MoneyTypePartialResponseDto type;
    BigDecimal value;

    protected MoneyPartialResponseDto() {
    }

    public MoneyPartialResponseDto(MoneyTypePartialResponseDto type, BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public MoneyTypePartialResponseDto getType() {
        return type;
    }
}
