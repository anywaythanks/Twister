package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class AccountFullDto implements Amount<MoneyFullDto>, Id, Number, General<GeneralAccountIdAndUuidDto>, CreatedOn, ModifiedBy {
    @NonNull MoneyFullDto amount;
    @NonNull Long id;
    @NonNull String number;
    @NonNull GeneralAccountIdAndUuidDto general;
    @NonNull Instant createdOn;
    @NonNull Instant modifiedBy;
}
