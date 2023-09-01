package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.account.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface AccountMapper {
    AccountPartialResponseDto toPartialDTO(Account account);

    @Mapping(source = "account.generalAccount", target = "general")
    AccountFullDto toFullDTO(Account account);

    @Mapping(source = "generalAccountId", target = "general")
    @Mapping(source = "account.id", target = "id")
    AccountFullDto toFullDTO(GeneralAccountIdResponseDto generalAccountId, Account account);

    AccountIdResponseDto toIdDTO(Account account);


    @Mapping(source = "account.generalAccount", target = "general")
    AccountDebitResponseDto toDebitDTO(Account account);

    AccountNumber toNumber(AccountNumberRequestDto number);

    AccountNumber toNumber(AccountRegisterDto accountRegisterDto);
    @Mapping(source = "generalId", target = "general")
    @Mapping(source = "accountNumber.number", target = "number")
    @Mapping(source = "money", target = "amount")
    AccountRegisterDto toRegister(GeneralAccountIdResponseDto generalId, AccountNumber accountNumber, Money money);

    @InheritInverseConfiguration
    Account toModel(AccountFullDto accountFullDto);

    AccountNumber toNumber(String number);
    @InheritInverseConfiguration
    String toNumber(AccountNumber number);
}
