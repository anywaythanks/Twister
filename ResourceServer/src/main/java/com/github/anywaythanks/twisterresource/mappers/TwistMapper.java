package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.CaseSlotName;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.TwistNumber;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseFullDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface TwistMapper {
    @Mapping(source = "wonSlotName.name", target = "wonSlotName")
    @Mapping(source = "twist.wonItem", target = "item")
    @Mapping(source = "twist.createdOn", target = "twistedOn")
    @Mapping(source = "twist.twistCase", target = "selectCase")
    @Mapping(source = "twist.quantityItem", target = "quantity")
    @Mapping(source = "twist.account.number", target = "account")
    @Mapping(source = "twist.number.number", target = "number")
    TwistPartialResponseDto toPartialDTO(CaseSlotName wonSlotName, Twist<? extends Item> twist);

    @Mapping(source = "number.number", target = "number")
    TwistRegisterDto toRegister(TwistNumber number, GeneralAccountIdAndUuidDto generalAccount,
                                CaseSlotFullDto slot, CaseFullDto selectCase, AccountFullDto account);

    TwistNumber toNumber(String number);
}
