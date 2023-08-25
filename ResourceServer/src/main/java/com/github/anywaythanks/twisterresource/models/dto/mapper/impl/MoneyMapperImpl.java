package com.github.anywaythanks.twisterresource.models.dto.mappers.impl;

import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MoneyMapperImpl implements MoneyMapper {
    private final MoneyTypeMapper moneyTypeMapper;

    public MoneyMapperImpl(MoneyTypeMapper moneyTypeMapper) {
        this.moneyTypeMapper = moneyTypeMapper;
    }

    public MoneyCreateRequestDto toRequest(Money money) {
        return new MoneyCreateRequestDto(moneyTypeMapper.toName(money.getTypeMoney()), money.getValue());
    }


    public MoneyPartialResponseDto toPartialDTO(Money money) {
        return new MoneyPartialResponseDto(moneyTypeMapper.toPartialDTO(money.getTypeMoney()), money.getValue());
    }

    public Money toMoney(MoneyType type, MoneyCreateRequestDto request) {
        return new Money(request.getValue(), type);
    }
}
