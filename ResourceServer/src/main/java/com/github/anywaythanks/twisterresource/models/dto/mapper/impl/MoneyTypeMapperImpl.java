package com.github.anywaythanks.twisterresource.models.dto.mapper.impl;

import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class MoneyTypeMapperImpl implements MoneyTypeMapper {
    public MoneyDTO.Type.Response.Id toIdDTO(MoneyType moneyType) {
        return new MoneyDTO.Type.Response.Id(moneyType.getId());
    }

    public MoneyDTO.Type.Response.Partial toPartialDTO(MoneyType moneyType) {
        return new MoneyDTO.Type.Response.Partial(moneyType.getName(), moneyType.getPathToIcon());
    }

    public MoneyDTO.Type.Request.Name toName(MoneyType moneyType) {
        return new MoneyDTO.Type.Request.Name(moneyType.getName());
    }

    public MoneyType toType(MoneyDTO.Type.Request.Name name, MoneyDTO.Type.Request.Create request) {
        return new MoneyType(name.getName(), request.getPathToIcon());
    }

    public Integer toId(MoneyDTO.Type.Response.Id id) {
        return id.getId();
    }
}
