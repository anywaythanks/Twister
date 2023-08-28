package com.github.anywaythanks.twisterresource.models.dto.account;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDebitResponseDto extends AccountIdResponseDto {
    public AccountDebitResponseDto(@NonNull Long id) {
        super(id);
    }
}
