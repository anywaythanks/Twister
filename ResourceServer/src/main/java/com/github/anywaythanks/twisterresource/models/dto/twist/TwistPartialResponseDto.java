package com.github.anywaythanks.twisterresource.models.dto.twist;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class TwistPartialResponseDto implements SelectCase<CaseNameResponseDto>, Account<AccountNumberResponseDto>, TwistedOn, Item<ItemPartialResponseDto>, Quantity, WonSlotName, Number {
    @NonNull CaseNameResponseDto selectCase;
    @NonNull AccountNumberResponseDto account;
    @NonNull Instant twistedOn;
    @NonNull ItemPartialResponseDto item;
    @NonNull Integer quantity;
    @NonNull String wonSlotName;
    @NonNull String number;
}
