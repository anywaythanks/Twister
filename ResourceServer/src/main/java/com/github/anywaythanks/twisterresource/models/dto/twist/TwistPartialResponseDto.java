package com.github.anywaythanks.twisterresource.models.dto.twist;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import lombok.*;

import java.time.Instant;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class TwistPartialResponseDto implements SelectCase<CaseNameResponseDto>, Account<AccountNumberResponseDto>,
        TwistedOn, Item<ItemPartialResponseDto>, Quantity, WonSlotName {
    @NonNull
    CaseNameResponseDto selectCase;
    @NonNull
    AccountNumberResponseDto account;
    @NonNull
    Instant twistedOn;
    @NonNull
    ItemPartialResponseDto item;
    @NonNull
    Integer quantity;
    @NonNull
    String wonSlotName;
}
