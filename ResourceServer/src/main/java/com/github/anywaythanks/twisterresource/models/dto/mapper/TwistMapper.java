package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.dto.TwistDTO;
import org.springframework.stereotype.Component;

@Component
public class TwistMapper {
    private final ItemMapper itemMapper;
    private final CaseMapper caseMapper;
    private final AccountMapper accountMapper;

    public TwistMapper(ItemMapper itemMapper, CaseMapper caseMapper, AccountMapper accountMapper) {
        this.itemMapper = itemMapper;
        this.caseMapper = caseMapper;
        this.accountMapper = accountMapper;
    }

    public TwistDTO.Response.Partial toDTO(Twist<?> twist) {
        return new TwistDTO.Response.Partial(
                caseMapper.toName(twist.getTwistCase()),
                accountMapper.toNumber(twist.getAccount()),
                twist.getCreatedOn(),
                itemMapper.toPartialDTO(twist.getWonItem()),
                twist.getQuantityItem());
    }
}
