package com.github.anywaythanks.twisterresource.models.dto.mappers.impl;

import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.dto.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.TwistMapper;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TwistMapperImpl implements TwistMapper {
    private final com.github.anywaythanks.twisterresource.models.dto.mappers.ItemMapper itemMapper;
    private final com.github.anywaythanks.twisterresource.models.dto.mappers.CaseMapper caseMapper;
    private final com.github.anywaythanks.twisterresource.models.dto.mappers.AccountMapper accountMapper;

    public TwistMapperImpl(ItemMapper itemMapper, CaseMapper caseMapper, AccountMapper accountMapper) {
        this.itemMapper = itemMapper;
        this.caseMapper = caseMapper;
        this.accountMapper = accountMapper;
    }

    public TwistPartialResponseDto toDTO(CaseSlot<?> wonSlot, Twist<?> twist) {
        return new TwistPartialResponseDto(
                caseMapper.toName(twist.getTwistCase()),
                accountMapper.toNumber(twist.getAccount()),
                twist.getCreatedOn(),
                itemMapper.toPartialDTO(twist.getWonItem()),
                twist.getQuantityItem(),
                wonSlot.getName().getName());
    }
}
