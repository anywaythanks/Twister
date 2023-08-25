package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;

import java.util.List;

public interface MoneyTypeInformationService {
    List<MoneyTypePartialResponseDto> listPartial();

    MoneyTypePartialResponseDto getPartial(MoneyTypeNameRequestDto name);

    MoneyTypeIdResponseDto getId(MoneyTypeNameRequestDto name);
}
