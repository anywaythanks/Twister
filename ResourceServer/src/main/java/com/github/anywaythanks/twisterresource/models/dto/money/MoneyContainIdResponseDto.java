package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;

import java.math.BigDecimal;

public class MoneyContainIdResponseDto implements Value, GetterType<MoneyTypeIdResponseDto> {
    MoneyTypeIdResponseDto type;
    BigDecimal value;

    protected MoneyContainIdResponseDto() {
    }

    public MoneyContainIdResponseDto(MoneyTypeIdResponseDto type, BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public MoneyTypeIdResponseDto getType() {
        return type;
    }
}
