package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = MoneyTypeMapper.class, componentModel = "spring")
public interface MoneyMapper {
    @Mapping(source = "money.moneyType", target = "type")
    MoneyCreateRequestDto toRequest(Money money);

    @Mapping(source = "money.moneyType", target = "type")
    MoneyPartialResponseDto toPartialDTO(Money money);

    @Mapping(source = "type", target = "moneyType")
    Money toMoney(MoneyType type, MoneyCreateRequestDto request);
}
