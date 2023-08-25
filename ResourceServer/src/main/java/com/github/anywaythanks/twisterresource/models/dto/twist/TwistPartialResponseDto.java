package com.github.anywaythanks.twisterresource.models.dto.twist;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;

import java.time.Instant;

public class TwistPartialResponseDto implements SelectCase<CaseNameResponseDto>, Account<AccountNumberResponseDto>,
        TwistedOn, Item<ItemPartialResponseDto>, Quantity, CaseName {
    CaseNameResponseDto selectCase;
    AccountNumberResponseDto account;
    Instant twistedOn;
    ItemPartialResponseDto item;
    Integer quantity;
    String caseName;

    protected TwistPartialResponseDto() {
    }

    public TwistPartialResponseDto(CaseNameResponseDto selectCase,
                                   AccountNumberResponseDto account,
                                   Instant twistedOn,
                                   ItemPartialResponseDto item,
                                   Integer quantity,
                                   String caseName) {
        this.selectCase = selectCase;
        this.account = account;
        this.twistedOn = twistedOn;
        this.item = item;
        this.quantity = quantity;
        this.caseName = caseName;
    }

    @Override
    public CaseNameResponseDto getSelectCase() {
        return selectCase;
    }

    @Override
    public AccountNumberResponseDto getAccount() {
        return account;
    }

    @Override
    public Instant getTwistedOn() {
        return twistedOn;
    }

    @Override
    public ItemPartialResponseDto getItem() {
        return item;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String getCaseName() {
        return caseName;
    }
}
