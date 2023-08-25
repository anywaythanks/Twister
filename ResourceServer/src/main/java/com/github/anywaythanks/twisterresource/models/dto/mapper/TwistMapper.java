package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.dto.TwistDTO;

public interface TwistMapper {
    TwistDTO.Response.Partial toDTO(CaseSlot<?> wonSlot, Twist<?> twist);
}
