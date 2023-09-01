package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.dto.general.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.Instant;

@Mapper(config = MapstructConfig.class)
public interface GeneralAccountMapper {
    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountPartialResponseDto toPartialDTO(GeneralAccount account);

    @Mapping(source = "account.userUuid", target = "uuid")
    GeneralAccountIdResponseDto toIdDTO(GeneralAccount account);

    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountPublicResponseDto toPublicDTO(GeneralAccount account);

    //    @Mappings({
//            @Mapping(source = "uuid", target = "userUuid"),
//            @Mapping(source = "name", target = "name"),
//            @Mapping(source = "now", target = "modifiedBy"),
//            @Mapping(source = "now", target = "createdOn")
//    })
//    GeneralAccount toAccount(Instant now, String uuid, GeneralAccountNameRequestDto name,
//                             GeneralAccountCreateRequestDto request);
    @Mapping(source = "id.uuid", target = "userUuid")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    GeneralAccount toAccount(GeneralAccountIdResponseDto id);

    GeneralAccountName toName(GeneralAccountNameRequestDto name);

    @Mapping(source = "account.name.name", target = "name")
    GeneralAccountNameResponseDto toName(GeneralAccount account);
}