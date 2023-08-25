package com.github.anywaythanks.twisterresource.models.dto.mappers;

import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;

public interface TwistMapper {
    TwistPartialResponseDto toDTO(CaseSlot<?> wonSlot, Twist<?> twist);
}
