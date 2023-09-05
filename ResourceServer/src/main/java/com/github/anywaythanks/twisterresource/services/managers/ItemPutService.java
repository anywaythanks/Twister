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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemPutService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyMapper moneyMapper;
    private final ItemRegisterService itemRegisterService;
    private final ItemMergeService itemMergeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ItemPartialResponseDto put(ItemNameRequestDto name, ItemCreateRequestDto create) {
        Optional<Item> optionalItem = itemRepository.findByName(name.getName());
        if (optionalItem.isEmpty()) {
            ItemRegisterDto item;
            if (create instanceof ItemMoneyCreateRequestDto createMoney) {
                MoneyTypeFullDto type = moneyTypeInformationService.getFull(createMoney.getCost().getType());
                MoneyFullDto cost = moneyMapper.toFull(type, createMoney.getCost());
                item = itemMapper.toRegister(cost, name, createMoney);
            } else if (create instanceof ItemTrashCreateRequestDto createTrash) {
                item = itemMapper.toRegister(createTrash, name);
            } else throw new ItemNotTypeException();
            return itemRegisterService.register(item);
        }
        Item item = optionalItem.get();
        if (create instanceof ItemMoneyCreateRequestDto createMoney) {
            if (item instanceof ItemMoney itemMoney) {
                MoneyTypeFullDto type = moneyTypeInformationService.getFull(createMoney.getCost().getType());
                MoneyFullDto cost = moneyMapper.toFull(type, createMoney.getCost());
                itemMoney.setCost(moneyMapper.toMoney(cost));
                itemMoney.setVisibleName(createMoney.getVisibleName());
            }
        } else if (create instanceof ItemTrashCreateRequestDto createTrash) {
            if (item instanceof ItemTrash itemTrash) {
                itemTrash.setVisibleName(createTrash.getVisibleName());
            }
        }
        return itemMergeService.merge(itemMapper.toFullItem(item));
    }
}
