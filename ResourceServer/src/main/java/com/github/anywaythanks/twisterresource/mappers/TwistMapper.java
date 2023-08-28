package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import org.mapstruct.Mapper;


public interface TwistMapper {
    TwistPartialResponseDto toDTO(CaseSlot<?> wonSlot, Twist<?> twist);
}
