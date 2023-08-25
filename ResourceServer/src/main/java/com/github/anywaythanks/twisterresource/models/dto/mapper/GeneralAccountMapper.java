package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.GeneralAccountName;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import org.springframework.stereotype.Component;

public interface GeneralAccountMapper {

     GeneralAccountDTO.Response.Partial toPartialDTO(GeneralAccount account);

     GeneralAccountDTO.Response.Id toIdDTO(GeneralAccount account);

     GeneralAccountDTO.Response.Public toPublicDTO(GeneralAccount account);

     GeneralAccount toAccount(String uuid, GeneralAccountDTO.Request.Name name,
                                    GeneralAccountDTO.Request.Create request);

     Long toId(GeneralAccountDTO.Response.Id id);

     GeneralAccountName toName(GeneralAccountDTO.Request.Name name);

     GeneralAccountDTO.Response.Name toName(GeneralAccount account);

}