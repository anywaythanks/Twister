package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import org.mapstruct.*;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueCheckStrategy.ON_IMPLICIT_CONVERSION;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(config = MapstructConfig.class)
public interface MoneyMapper {
    @Mapping(source = "money.moneyType", target = "type")
    MoneyCreateRequestDto toRequest(Money money);

    @Mapping(source = "money.moneyType", target = "type")
    MoneyPartialResponseDto toPartialDTO(Money money);

    @Mapping(source = "moneyFullDto.type", target = "moneyType")
    @Mapping(target = "add", ignore = true)
    @Mapping(target = "multiply", ignore = true)
    @Mapping(target = "subtract", ignore = true)
    Money toMoney(MoneyFullDto moneyFullDto);

    @InheritInverseConfiguration
    MoneyFullDto toFull(Money money);

    @Mapping(source = "type", target = "type")
    MoneyFullDto toFull(MoneyTypeFullDto type, MoneyCreateRequestDto request);
}
