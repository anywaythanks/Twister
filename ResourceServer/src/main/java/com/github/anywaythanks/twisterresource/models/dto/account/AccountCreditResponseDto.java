package com.github.anywaythanks.twisterresource.models.dto.account;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountCreditResponseDto extends AccountIdResponseDto {
    public AccountCreditResponseDto(@NonNull Long id) {
        super(id);
    }
}
