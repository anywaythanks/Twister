package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.dto.item.*;
import com.github.anywaythanks.twisterresource.models.dto.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterItemServiceImpl implements RegisterItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final MoneyTypeMapper moneyTypeMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeRepository moneyTypeRepository;

    public RegisterItemServiceImpl(ItemRepository itemRepository,
                                   ItemMapper itemMapper, MoneyTypeMapper moneyTypeMapper,
                                   MoneyTypeInformationService moneyTypeInformationService,
                                   MoneyTypeRepository moneyTypeRepository) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.moneyTypeMapper = moneyTypeMapper;
        this.moneyTypeInformationService = moneyTypeInformationService;
        this.moneyTypeRepository = moneyTypeRepository;
    }

    public ItemPartialResponseDto merge(ItemNameRequestDto name, ItemCreateRequestDto mergeItem) {
        var item = switch (mergeItem.getType()) {
            case MONEY -> {
                if (mergeItem instanceof ItemMoneyCreateRequestDto itemMoney) {
                    var type = moneyTypeRepository.findById(moneyTypeMapper
                                    .toId(moneyTypeInformationService.getId(itemMoney.getCost().getType())))
                            .orElseThrow(NotFoundException::new);
                    yield itemMapper.toItemMoney(name, type, itemMoney);
                }
                throw new ItemNotTypeException();
            }
            case TRASH -> {
                if (mergeItem instanceof ItemTrashCreateRequestDto itemTrash)
                    yield itemMapper.toItemTrash(name, itemTrash);
                throw new ItemNotTypeException();
            }
        };
        itemRepository.findByName(name.getName()).ifPresent(it -> {
            if (it.getClass().isInstance(item)) throw new ItemNotTypeException();
        });
        return itemMapper.toPartialDTO(itemRepository.save(item));
    }
}