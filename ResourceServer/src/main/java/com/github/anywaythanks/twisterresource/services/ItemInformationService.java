package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemInformationService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemPartialResponseDto getPartial(ItemNameRequestDto name) {
        return itemMapper.toPartialDTO(itemRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }

    public ItemIdResponseDto getId(ItemNameRequestDto name) {
        return itemMapper.toIdDTO(itemRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }

    public List<ItemPartialResponseDto> listPartial() {
        return itemRepository.findAll().stream().map(itemMapper::toPartialDTO).toList();
    }
}
