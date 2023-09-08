package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.auth.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface GeneralAccountMapper {
    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountPartialResponseDto toPartialDTO(GeneralAccount account);

    @Mapping(source = "account.userUuid", target = "uuid")
    GeneralAccountIdAndUuidDto toIdDTO(GeneralAccount account);

    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountPublicResponseDto toPublicDTO(GeneralAccount account);

    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "id.uuid", target = "userUuid")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    GeneralAccount toAccount(GeneralAccountIdAndUuidDto id);

    GeneralAccountName toName(GeneralAccountNameRequestDto name);

    GeneralAccountNameRequestDto toNameRequest(GeneralAccountName name);

    GeneralAccountName toName(String name);

    @Mapping(source = "fullDto.uuid", target = "userUuid")
    GeneralAccount toAccount(GeneralAccountFullDto fullDto);

    @Mapping(source = "account.name.name", target = "name")
    @Mapping(source = "account.userUuid", target = "uuid")
    GeneralAccountFullDto toFull(GeneralAccount account);

    GeneralAccountRegisterDto toRegister(UserPrincipal userPrincipal, GeneralAccountName name, GeneralAccountCreateRequestDto create);

    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountNameResponseDto toName(GeneralAccount account);
}