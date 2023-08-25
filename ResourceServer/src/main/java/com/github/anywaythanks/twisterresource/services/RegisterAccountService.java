package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.account.AccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;

public interface RegisterAccountService {
    AccountPartialResponseDto merge(GeneralAccountNameRequestDto name, AccountNumberRequestDto number,
                                    AccountCreateRequestDto create);

    AccountPartialResponseDto register(GeneralAccountNameRequestDto name, AccountCreateRequestDto create);
}
