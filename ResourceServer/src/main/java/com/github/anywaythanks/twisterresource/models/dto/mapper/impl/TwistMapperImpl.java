package com.github.anywaythanks.twisterresource.models.dto.mapper.impl;

import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.dto.TwistDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.CaseMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.TwistMapper;
import org.springframework.stereotype.Component;

@Component
public class TwistMapperImpl implements TwistMapper {
    private final com.github.anywaythanks.twisterresource.models.dto.mapper.ItemMapper itemMapper;
    private final com.github.anywaythanks.twisterresource.models.dto.mapper.CaseMapper caseMapper;
    private final com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper accountMapper;

    public TwistMapperImpl(ItemMapper itemMapper, CaseMapper caseMapper, AccountMapper accountMapper) {
        this.itemMapper = itemMapper;
        this.caseMapper = caseMapper;
        this.accountMapper = accountMapper;
    }

    public TwistDTO.Response.Partial toDTO(CaseSlot<?> wonSlot, Twist<?> twist) {
        return new TwistDTO.Response.Partial(
                caseMapper.toName(twist.getTwistCase()),
                accountMapper.toNumber(twist.getAccount()),
                twist.getCreatedOn(),
                itemMapper.toPartialDTO(twist.getWonItem()),
                twist.getQuantityItem(),
                wonSlot.getName().getName());
    }
}
