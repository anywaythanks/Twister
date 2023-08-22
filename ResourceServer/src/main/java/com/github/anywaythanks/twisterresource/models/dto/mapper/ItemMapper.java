package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    private final MoneyMapper moneyMapper;

    public ItemMapper(MoneyMapper moneyMapper) {
        this.moneyMapper = moneyMapper;
    }

    public ItemDTO.Response.Partial toPartialDTO(Item item) {
        if (item instanceof ItemMoney itemMoney)
            return new ItemDTO.Response.PartialMoney(ItemDTO.Types.MONEY, moneyMapper.toPartialDTO(itemMoney.getCost()),
                    itemMoney.getName(), itemMoney.getVisibleName());
        else if (item instanceof ItemTrash itemTrash)
            return new ItemDTO.Response.PartialTrash(ItemDTO.Types.TRASH,
                    itemTrash.getName(), itemTrash.getVisibleName());
        else throw new ItemNotTypeException();
    }

    public ItemMoney toItemMoney(ItemDTO.Request.Name name, MoneyType moneyType, ItemDTO.Request.CreateMoney itemMoney) {
        return new ItemMoney(name.getName(), itemMoney.getVisibleName(), moneyMapper.toMoney(moneyType, itemMoney.getCost()));
    }
    public ItemTrash toItemTrash(ItemDTO.Request.Name name, ItemDTO.Request.CreateTrash itemMoney) {
        return new ItemTrash(name.getName(), itemMoney.getVisibleName());
    }

    public ItemDTO.Response.Id toIdDTO(Item item) {
        return new ItemDTO.Response.Id(item.getId());
    }

    public Long toId(ItemDTO.Response.Id id) {
        return id.getId();
    }
}
