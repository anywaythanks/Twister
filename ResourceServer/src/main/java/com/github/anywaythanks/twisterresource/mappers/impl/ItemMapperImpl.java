package com.github.anywaythanks.twisterresource.mappers.impl;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.item.*;
import org.springframework.stereotype.Component;

@Component
public class ItemMapperImpl implements ItemMapper {
    private final MoneyMapper moneyMapper;

    public ItemMapperImpl(MoneyMapper moneyMapper) {
        this.moneyMapper = moneyMapper;
    }

    public ItemPartialResponseDto toPartialDTO(Item item) {
        if (item instanceof ItemMoney itemMoney)
            return new ItemMoneyPartialResponseDto(ItemTypes.MONEY,
                    itemMoney.getName(), itemMoney.getVisibleName(), moneyMapper.toPartialDTO(itemMoney.getCost()));
        else if (item instanceof ItemTrash itemTrash)
            return new ItemTrashPartialResponseDto(ItemTypes.TRASH,
                    itemTrash.getName(), itemTrash.getVisibleName());
        else throw new ItemNotTypeException();
    }

    public ItemMoney toItemMoney(ItemNameRequestDto name, MoneyType moneyType, ItemMoneyCreateRequestDto itemMoney) {
        return new ItemMoney(name.getName(), itemMoney.getVisibleName(), moneyMapper.toMoney(moneyType, itemMoney.getCost()));
    }

    public ItemTrash toItemTrash(ItemNameRequestDto name, ItemTrashCreateRequestDto itemMoney) {
        return new ItemTrash(name.getName(), itemMoney.getVisibleName());
    }

    public ItemIdResponseDto toIdDTO(Item item) {
        return new ItemIdResponseDto(item.getId());
    }

    public String toName(ItemNameRequestDto name) {
        return name.getName();
    }

    public ItemNameRequestDto toNameDTO(Item item) {
        return new ItemNameRequestDto(item.getName());
    }

    public Long toId(ItemIdResponseDto id) {
        return id.getId();
    }
}
