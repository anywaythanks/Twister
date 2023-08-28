package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class AccountPartialResponseDto implements Amount<MoneyPartialResponseDto>, Number {
    @NonNull
    MoneyPartialResponseDto amount;
    @NonNull
    String number;
}
