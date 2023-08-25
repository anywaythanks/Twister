package com.github.anywaythanks.twisterresource.models.dto.money;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;

import java.math.BigDecimal;

public class MoneyCreateRequestDto implements Value, NameType<MoneyTypeNameRequestDto> {
    MoneyTypeNameRequestDto type;
    BigDecimal value;

    protected MoneyCreateRequestDto() {
    }

    public MoneyCreateRequestDto(MoneyTypeNameRequestDto type, BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public MoneyTypeNameRequestDto getType() {
        return type;
    }
}
