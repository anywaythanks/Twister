package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface MoneyTypeMapper {
    MoneyTypeIdResponseDto toIdDTO(MoneyType moneyType);

    MoneyTypePartialResponseDto toPartialDTO(MoneyType moneyType);

    MoneyTypeNameRequestDto toName(MoneyType moneyType);

    MoneyType toType(MoneyTypeNameRequestDto name, MoneyTypeCreateRequestDto request);
}
