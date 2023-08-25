package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;

public class AccountCreateRequestDto implements NameType<MoneyTypeNameRequestDto> {
    MoneyTypeNameRequestDto type;

    @Override
    public MoneyTypeNameRequestDto getType() {
        return type;
    }
}
