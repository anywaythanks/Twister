package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import org.springframework.stereotype.Component;

@Component
public class GeneralAccountMapper {

    public GeneralAccountDTO.Response.Partial toPartialDTO(GeneralAccount account) {
        return new GeneralAccountDTO.Response.Partial(account.getName().getName(), account.getNickname());
    }

    public GeneralAccountDTO.Response.Id toIdDTO(GeneralAccount account) {
        return new GeneralAccountDTO.Response.Id(account.getId(), account.getUserUuid());
    }

    public GeneralAccountDTO.Response.Public toPublicDTO(GeneralAccount account) {
        return new GeneralAccountDTO.Response.Public(account.getName().getName(), account.getNickname());
    }

    public GeneralAccount toAccount(String uuid, GeneralAccountDTO.Request.Name name, GeneralAccountDTO.Request.Create request) {
        return new GeneralAccount(uuid, request.getNickname(), toName(name));
    }

    public Long toId(GeneralAccountDTO.Response.Id id) {
        return id.getId();
    }

    public GeneralAccountName toName(GeneralAccountDTO.Request.Name name) {
        return new GeneralAccountName(name.getName());
    }

    public GeneralAccountDTO.Response.Name toName(GeneralAccount account) {
        return new GeneralAccountDTO.Response.Name(account.getName().getName());
    }
}
