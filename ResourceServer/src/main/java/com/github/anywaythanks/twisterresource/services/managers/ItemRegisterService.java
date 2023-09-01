package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.dto.item.*;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemRegisterService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyMapper moneyMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ItemPartialResponseDto merge(ItemNameRequestDto name, ItemCreateRequestDto itemDto) {
        Instant now = Instant.now();
        Item mergedItem = switch (itemDto.getType()) {
            case MONEY -> {
                if (itemDto instanceof ItemMoneyCreateRequestDto itemMoney) {
                    MoneyTypeFullDto type = moneyTypeInformationService.getFull(itemMoney.getCost().getType());
                    MoneyFullDto fullMoney = moneyMapper.toFull(type, itemMoney.getCost());
                    yield new ItemMoney(name.getName(), itemDto.getVisibleName(), now, now, moneyMapper.toMoney(fullMoney));
                }
                throw new ItemNotTypeException();
            }
            case TRASH -> {
                if (itemDto instanceof ItemTrashCreateRequestDto itemTrash)
                    yield new ItemTrash(name.getName(), itemTrash.getVisibleName(), now, now);
                throw new ItemNotTypeException();
            }
        };
        Optional<Item> optionalItem = itemRepository.findByName(name.getName());
        optionalItem.ifPresent(item -> {
            if (!item.getClass().isInstance(mergedItem)) throw new ItemNotTypeException();
            item.setVisibleName(mergedItem.getVisibleName());
            item.setModifiedBy(Instant.now());
            if (item instanceof ItemMoney itemMoney) {
                if (mergedItem instanceof ItemMoney mergedItemMoney) {
                    itemMoney.setCost(mergedItemMoney.getCost());
                }
            }
        });
        Item resultItem = optionalItem.orElse(mergedItem);
        return itemMapper.toPartialDTO(itemRepository.save(resultItem));
    }
}
