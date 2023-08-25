package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.ItemMapper;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.services.ItemInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInformationServiceImpl implements ItemInformationService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemInformationServiceImpl(ItemRepository itemRepository,
                                      ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDTO.Response.Partial getPartial(ItemDTO.Request.Name name) {
        return itemMapper.toPartialDTO(itemRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }

    public ItemDTO.Response.Id getId(ItemDTO.Request.Name name) {
        return itemMapper.toIdDTO(itemRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }

    public List<ItemDTO.Response.Partial> listPartial() {
        return itemRepository.findAll().stream().map(itemMapper::toPartialDTO).toList();
    }
}
