package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;

import java.util.List;

public interface ItemInformationService {
    ItemPartialResponseDto getPartial(ItemNameRequestDto name);

    ItemIdResponseDto getId(ItemNameRequestDto name);

    List<ItemPartialResponseDto> listPartial();
}
