package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RegisterService
@RequiredArgsConstructor
public class MoneyRegisterService {
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeMapper moneyTypeMapper;

    public Money register(MoneyTypeNameRequestDto typeName) {
        MoneyTypeFullDto moneyTypeId = moneyTypeInformationService.getFull(typeName);
        return Money.builder()
                .moneyType(moneyTypeMapper.toType(moneyTypeId))
                .value(BigDecimal.ZERO)
                .build();
    }
}
