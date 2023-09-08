package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@MergeService
@RequiredArgsConstructor
public class ItemMergeService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final Clock clock;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ItemPartialResponseDto merge(ItemFullDto itemFullDto) {
        Item item = itemMapper.toItem(itemFullDto);
        item.setModifiedBy(Instant.now(clock));
        Item resultItem = itemRepository.save(item);
        return itemMapper.toPartialDTO(itemRepository.save(resultItem));
    }
}
