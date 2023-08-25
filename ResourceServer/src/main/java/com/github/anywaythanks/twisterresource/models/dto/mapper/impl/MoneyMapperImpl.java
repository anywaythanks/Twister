package com.github.anywaythanks.twisterresource.models.dto.mapper.impl;

import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class MoneyMapperImpl implements MoneyMapper {
    private final MoneyTypeMapper moneyTypeMapper;

    public MoneyMapperImpl(MoneyTypeMapper moneyTypeMapper) {
        this.moneyTypeMapper = moneyTypeMapper;
    }

    public MoneyDTO.Request.Create toRequest(Money money) {
        return new MoneyDTO.Request.Create(moneyTypeMapper.toName(money.getTypeMoney()), money.getValue());
    }


    public MoneyDTO.Response.Partial toPartialDTO(Money money) {
        return new MoneyDTO.Response.Partial(moneyTypeMapper.toPartialDTO(money.getTypeMoney()), money.getValue());
    }

    public Money toMoney(MoneyType type, MoneyDTO.Request.Create request) {
        return new Money(request.getValue(), type);
    }
}
