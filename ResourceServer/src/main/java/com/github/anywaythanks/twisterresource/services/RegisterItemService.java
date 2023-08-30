package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.dto.item.*;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeRepository moneyTypeRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ItemPartialResponseDto merge(ItemNameRequestDto name, ItemCreateRequestDto itemDto) {
        Item mergedItem = switch (itemDto.getType()) {
            case MONEY -> {
                if (itemDto instanceof ItemMoneyCreateRequestDto itemMoney) {
                    var type = moneyTypeRepository.findById(moneyTypeInformationService.getId(itemMoney.getCost().getType()).getId())
                            .orElseThrow(NotFoundException::new);
                    yield itemMapper.toItemMoney(name, type, itemMoney);
                }
                throw new ItemNotTypeException();
            }
            case TRASH -> {
                if (itemDto instanceof ItemTrashCreateRequestDto itemTrash)
                    yield itemMapper.toItemTrash(name, itemTrash);
                throw new ItemNotTypeException();
            }
        };
        Optional<Item> optionalItem = itemRepository.findByName(name.getName());
        optionalItem.ifPresent(item -> {
            if (!item.getClass().isInstance(mergedItem)) throw new ItemNotTypeException();
            item.setVisibleName(mergedItem.getVisibleName());
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
