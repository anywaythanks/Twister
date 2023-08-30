package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface MoneyTypeMapper {
    MoneyTypeIdResponseDto toIdDTO(MoneyType moneyType);

    MoneyTypePartialResponseDto toPartialDTO(MoneyType moneyType);

    MoneyTypeNameRequestDto toName(MoneyType moneyType);

    @Mappings({
            @Mapping(source = "now", target = "modifiedBy"),
            @Mapping(source = "now", target = "createdOn")
    })
    MoneyType toType(Instant now, MoneyTypeNameRequestDto name, MoneyTypeCreateRequestDto request);
}
