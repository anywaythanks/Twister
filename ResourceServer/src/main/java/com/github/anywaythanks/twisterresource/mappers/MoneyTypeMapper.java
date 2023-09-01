package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.type.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.Instant;

@Mapper(config = MapstructConfig.class)
public interface MoneyTypeMapper {
    MoneyTypeIdResponseDto toIdDTO(MoneyType moneyType);

    MoneyTypePartialResponseDto toPartialDTO(MoneyType moneyType);

    MoneyTypeFullDto toFullDto(MoneyType moneyType);

    MoneyTypeNameRequestDto toName(MoneyType moneyType);
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "pathToIcon", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    MoneyType toType(MoneyTypeIdResponseDto id);

    MoneyType toType(MoneyTypeFullDto full);
}
