package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import org.springframework.stereotype.Component;

public interface MoneyMapper {

    MoneyDTO.Request.Create toRequest(Money money);

    MoneyDTO.Response.Partial toPartialDTO(Money money);

    Money toMoney(MoneyType type, MoneyDTO.Request.Create request);
}
