package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.dto.account.*;

public interface AccountMapper {
     AccountPartialResponseDto toPartialDTO(Account account);
     AccountIdResponseDto toIdDTO(Account account);

     AccountDebitResponseDto toDebitDTO(Account account);
     AccountCreditResponseDto toCreditDTO(Account account);

     Long toId(AccountIdResponseDto id);

     AccountNumber toNumber(AccountNumberRequestDto number);

     AccountNumberResponseDto toNumber(Account account);
}
