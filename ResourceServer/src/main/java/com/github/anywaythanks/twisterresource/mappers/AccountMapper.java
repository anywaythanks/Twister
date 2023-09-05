package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.account.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface AccountMapper {
    @Mapping(source = "account.number.number", target = "number")
    AccountPartialResponseDto toPartialDTO(Account account);

    @Mapping(source = "account.generalAccount", target = "general")
    @Mapping(source = "account.number.number", target = "number")
    AccountFullDto toFullDTO(Account account);

    @Mapping(source = "generalAccountId", target = "general")
    @Mapping(source = "account.number.number", target = "number")
    @Mapping(source = "account.id", target = "id")
    AccountFullDto toFullDTO(GeneralAccountIdAndUuidDto generalAccountId, Account account);

    AccountIdDto toIdDTO(Account account);


    @Mapping(source = "account.generalAccount", target = "general")
    @Mapping(source = "account.number.number", target = "number")
    AccountDebitResponseDto toDebitDTO(Account account);

    AccountNumber toNumber(AccountNumberRequestDto number);

    AccountNumber toNumber(AccountRegisterDto accountRegisterDto);

    @Mapping(source = "generalId", target = "general")
    @Mapping(source = "accountNumber.number", target = "number")
    @Mapping(source = "money", target = "amount")
    AccountRegisterDto toRegister(GeneralAccountIdAndUuidDto generalId, AccountNumber accountNumber, Money money);

    @InheritInverseConfiguration
    Account toAccount(AccountFullDto accountFullDto);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "generalAccount", ignore = true)
    Account toAccount(AccountIdDto id);

    AccountNumber toNumber(String number);
}
