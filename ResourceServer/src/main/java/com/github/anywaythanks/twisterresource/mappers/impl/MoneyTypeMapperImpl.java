package com.github.anywaythanks.twisterresource.mappers.impl;

import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MoneyTypeMapperImpl implements MoneyTypeMapper {
    public MoneyTypeIdResponseDto toIdDTO(MoneyType moneyType) {
        return new MoneyTypeIdResponseDto(moneyType.getId());
    }

    public MoneyTypePartialResponseDto toPartialDTO(MoneyType moneyType) {
        return new MoneyTypePartialResponseDto(moneyType.getName(), moneyType.getPathToIcon());
    }

    public MoneyTypeNameRequestDto toName(MoneyType moneyType) {
        return new MoneyTypeNameRequestDto(moneyType.getName());
    }

    public MoneyType toType(MoneyTypeNameRequestDto name, MoneyTypeCreateRequestDto request) {
        return new MoneyType(name.getName(), request.getPathToIcon());
    }

    public Integer toId(MoneyTypeIdResponseDto id) {
        return id.getId();
    }
}
