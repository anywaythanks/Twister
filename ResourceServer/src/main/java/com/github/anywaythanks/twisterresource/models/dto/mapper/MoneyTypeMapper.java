package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import org.springframework.stereotype.Component;

public interface MoneyTypeMapper {
    MoneyDTO.Type.Response.Id toIdDTO(MoneyType moneyType);

    MoneyDTO.Type.Response.Partial toPartialDTO(MoneyType moneyType);

    MoneyDTO.Type.Request.Name toName(MoneyType moneyType);

    MoneyType toType(MoneyDTO.Type.Request.Name name, MoneyDTO.Type.Request.Create request);

    Integer toId(MoneyDTO.Type.Response.Id id);
}
