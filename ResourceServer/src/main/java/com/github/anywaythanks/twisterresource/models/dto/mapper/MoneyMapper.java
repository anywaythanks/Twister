package com.github.anywaythanks.twisterresource.models.dto.mappers;

import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;

public interface MoneyMapper {

    MoneyCreateRequestDto toRequest(Money money);

    MoneyPartialResponseDto toPartialDTO(Money money);

    Money toMoney(MoneyType type, MoneyCreateRequestDto request);
}
