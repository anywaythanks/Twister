package com.github.anywaythanks.twisterresource.models.dto.account;

public class AccountNumberRequestDto implements Number {
    String number;

    protected AccountNumberRequestDto() {
    }

    public AccountNumberRequestDto(String number) {
        this.number = number;
    }

    public AccountNumberRequestDto(Long number) {
        this.number = number.toString();
    }

    @Override
    public String getNumber() {
        return number;
    }
}
