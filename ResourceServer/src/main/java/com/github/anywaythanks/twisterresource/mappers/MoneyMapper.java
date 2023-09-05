package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface MoneyMapper {
    @Mapping(source = "money.moneyType", target = "type")
    MoneyCreateRequestDto toRequest(Money money);

    @Mapping(source = "money.moneyType", target = "type")
    MoneyPartialResponseDto toPartialDTO(Money money);

    @Mapping(source = "moneyFullDto.type", target = "moneyType")
    Money toMoney(MoneyFullDto moneyFullDto);

    @InheritInverseConfiguration
    MoneyFullDto toFull(Money money);

    @Mapping(source = "type", target = "type")
    MoneyFullDto toFull(MoneyTypeFullDto type, MoneyCreateRequestDto request);
}
