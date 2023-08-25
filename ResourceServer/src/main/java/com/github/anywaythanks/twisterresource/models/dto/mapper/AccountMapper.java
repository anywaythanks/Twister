package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import org.springframework.stereotype.Component;

public interface AccountMapper {
     AccountDTO.Response.Partial toPartialDTO(Account account);

     AccountDTO.Response.Id toIdDTO(Account account);

     AccountDTO.Response.Debit toDebitDTO(Account account);
     AccountDTO.Response.Credit toCreditDTO(Account account);

     Long toId(AccountDTO.Response.Id id);

     AccountNumber toNumber(AccountDTO.Request.Number number);

     AccountDTO.Response.Number toNumber(Account account);
}
