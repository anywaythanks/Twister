package com.github.anywaythanks.twisterresource.models.dto.account;

public class AccountIdResponseDto implements Id {
    Long id;

    protected AccountIdResponseDto() {

    }

    public AccountIdResponseDto(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
