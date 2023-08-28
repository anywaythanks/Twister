package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.dto.general.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GeneralAccountMapper {
    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountPartialResponseDto toPartialDTO(GeneralAccount account);
    @Mapping(source = "account.userUuid", target = "uuid")
    GeneralAccountIdResponseDto toIdDTO(GeneralAccount account);

    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountPublicResponseDto toPublicDTO(GeneralAccount account);

    @Mappings({
            @Mapping(source = "uuid", target = "userUuid"),
            @Mapping(source = "name", target = "name")
    })
    GeneralAccount toAccount(String uuid, GeneralAccountNameRequestDto name,
                             GeneralAccountCreateRequestDto request);

    GeneralAccountName toName(GeneralAccountNameRequestDto name);
    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountNameResponseDto toName(GeneralAccount account);
}