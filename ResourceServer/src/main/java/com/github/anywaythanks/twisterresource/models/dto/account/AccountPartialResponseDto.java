package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;

public class AccountPartialResponseDto implements Amount<MoneyPartialResponseDto>, Number {
    MoneyPartialResponseDto amount;
    String number;

    protected AccountPartialResponseDto() {

    }

    public AccountPartialResponseDto(String number, MoneyPartialResponseDto amount) {
        this.number = number;
        this.amount = amount;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public MoneyPartialResponseDto getAmount() {
        return amount;
    }
}
