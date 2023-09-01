package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDebitResponseDto extends AccountFullDto {
    public AccountDebitResponseDto(@NonNull MoneyFullDto amount,
                                   @NonNull Long id,
                                   @NonNull String number,
                                   @NonNull GeneralAccountIdResponseDto general,
                                   @NonNull Instant createdOn,
                                   @NonNull Instant modifiedBy) {
        super(amount, id, number, general, createdOn, modifiedBy);
    }
}
