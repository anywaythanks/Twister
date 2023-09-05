package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.annotation.ResponseDto;
import lombok.NonNull;

@ResponseDto
public class AccountCreditResponseDto extends AccountIdDto {
    public AccountCreditResponseDto(@NonNull Long id) {
        super(id);
    }
}
