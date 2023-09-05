package com.github.anywaythanks.twisterresource.models.dto.twist;

import com.github.anywaythanks.twisterresource.annotation.RegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseFullDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountIdDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@RegisterDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class TwistRegisterDto implements SelectCase<CaseFullDto>, Account<AccountFullDto>, WonSlot<CaseSlotFullDto>, GeneralAccount<GeneralAccountIdAndUuidDto> {
    @NonNull CaseFullDto selectCase;
    @NonNull AccountFullDto account;
    @NonNull GeneralAccountIdAndUuidDto generalAccount;
    @NonNull CaseSlotFullDto slot;
    @NonNull String number;
}
