package com.github.anywaythanks.twisterresource.mappers.impl;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.dto.general.*;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import org.springframework.stereotype.Component;

@Component
public class GeneralAccountMapperImpl implements GeneralAccountMapper {

    public GeneralAccountPartialResponseDto toPartialDTO(GeneralAccount account) {
        return new GeneralAccountPartialResponseDto(account.getName().getName(), account.getNickname());
    }

    public GeneralAccountIdResponseDto toIdDTO(GeneralAccount account) {
        return new GeneralAccountIdResponseDto(account.getId(), account.getUserUuid());
    }

    public GeneralAccountPublicResponseDto toPublicDTO(GeneralAccount account) {
        return new GeneralAccountPublicResponseDto(account.getName().getName(), account.getNickname());
    }

    public GeneralAccount toAccount(String uuid, GeneralAccountNameRequestDto name, GeneralAccountCreateRequestDto request) {
        return new GeneralAccount(uuid, request.getNickname(), toName(name));
    }

    public Long toId(GeneralAccountIdResponseDto id) {
        return id.getId();
    }

    public GeneralAccountName toName(GeneralAccountNameRequestDto name) {
        return new GeneralAccountName(name.getName());
    }

    public GeneralAccountNameResponseDto toName(GeneralAccount account) {
        return new GeneralAccountNameResponseDto(account.getName().getName());
    }
}
