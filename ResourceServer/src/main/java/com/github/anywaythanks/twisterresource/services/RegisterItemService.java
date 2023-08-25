package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;

public interface RegisterItemService {
    ItemPartialResponseDto merge(ItemNameRequestDto name, ItemCreateRequestDto mergeItem);
}
