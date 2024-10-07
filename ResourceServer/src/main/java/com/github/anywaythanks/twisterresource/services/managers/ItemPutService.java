package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.PutService;
import com.github.anywaythanks.twisterresource.exceptions.InvalidItemTypeException;
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

@PutService
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
            ItemRegisterDto item = switch (create) {
                case ItemMoneyCreateRequestDto createMoney -> {
                    MoneyTypeFullDto type = moneyTypeInformationService.getFull(createMoney.getCost().getType());
                    MoneyFullDto cost = moneyMapper.toFull(type, createMoney.getCost());
                    yield itemMapper.toRegister(cost, name, createMoney);
                }
                case ItemTrashCreateRequestDto createTrash -> itemMapper.toRegister(createTrash, name);
            };
            return itemRegisterService.register(item);
        }
        Item item = optionalItem.get();
        record Items(Item item, ItemCreateRequestDto create) {
        }

        switch (new Items(item, create)) {
            case Items(ItemMoney itemMoney, ItemMoneyCreateRequestDto createMoney) -> {
                MoneyTypeFullDto type = moneyTypeInformationService.getFull(createMoney.getCost().getType());
                MoneyFullDto cost = moneyMapper.toFull(type, createMoney.getCost());
                itemMoney.setCost(moneyMapper.toMoney(cost));
                itemMoney.setVisibleName(createMoney.getVisibleName());
            }
            case Items(ItemTrash itemTrash, ItemTrashCreateRequestDto createTrash) ->
                    itemTrash.setVisibleName(createTrash.getVisibleName());
            default -> {
            }
        }
        return itemMergeService.merge(itemMapper.toFullItem(item));
    }
}
