package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class AccountRegisterDto implements Amount<MoneyFullDto>, Number, General<GeneralAccountIdResponseDto> {
    @NonNull
    MoneyFullDto amount;
    @NonNull
    String number;
    @NonNull
    GeneralAccountIdResponseDto general;
}
