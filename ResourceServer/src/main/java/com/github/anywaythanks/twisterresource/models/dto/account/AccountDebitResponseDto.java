package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.annotation.ResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.NonNull;

import java.time.Instant;

@ResponseDto
public class AccountDebitResponseDto extends AccountFullDto {
    public AccountDebitResponseDto(@NonNull MoneyFullDto amount, @NonNull Long id, @NonNull String number, @NonNull GeneralAccountIdAndUuidDto general, @NonNull Instant createdOn, @NonNull Instant modifiedBy) {
        super(amount, id, number, general, createdOn, modifiedBy);
    }
}
