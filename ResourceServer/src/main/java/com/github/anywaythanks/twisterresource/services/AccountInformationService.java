package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.account.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;

import java.util.List;

public interface AccountInformationService {
    AccountPartialResponseDto getPartial(GeneralAccountNameRequestDto name,
                                         AccountNumberRequestDto number);

    AccountIdResponseDto getId(GeneralAccountNameRequestDto name,
                               AccountNumberRequestDto number);

    AccountDebitResponseDto getDebit(AccountNumberRequestDto number);

    AccountCreditResponseDto getCredit(GeneralAccountNameRequestDto name, AccountNumberRequestDto number);

    List<AccountPartialResponseDto> listPartial(GeneralAccountNameRequestDto name);
}
