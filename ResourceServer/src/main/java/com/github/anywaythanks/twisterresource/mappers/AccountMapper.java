package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.dto.account.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = MoneyMapper.class, componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "account.number.number", target = "number")
    AccountPartialResponseDto toPartialDTO(Account account);

    AccountIdResponseDto toIdDTO(Account account);

    AccountDebitResponseDto toDebitDTO(Account account);

    AccountCreditResponseDto toCreditDTO(Account account);

    AccountNumber toNumber(AccountNumberRequestDto number);

    @Mapping(source = "account.number.number", target = "number")
    AccountNumberResponseDto toNumber(Account account);
}
