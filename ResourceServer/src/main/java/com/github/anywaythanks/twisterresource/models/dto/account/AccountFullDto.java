package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class AccountFullDto implements Amount<MoneyFullDto>,
        Id, Number, General<GeneralAccountIdResponseDto>, CreatedOn, ModifiedBy {
    @NonNull
    MoneyFullDto amount;
    @NonNull
    Long id;
    @NonNull
    String number;
    @NonNull
    GeneralAccountIdResponseDto general;
    @NonNull
    Instant createdOn;
    @NonNull
    Instant modifiedBy;
}
