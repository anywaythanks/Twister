package com.github.anywaythanks.twisterresource.models.dto.account;

public class AccountNumberResponseDto implements Number {
    String number;

    public AccountNumberResponseDto(String number) {
        this.number = number;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
