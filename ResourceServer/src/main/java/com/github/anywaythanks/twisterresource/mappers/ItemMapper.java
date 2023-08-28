package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.item.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = MoneyMapper.class, componentModel = "spring")
public interface ItemMapper {
    default ItemPartialResponseDto toPartialDTO(Item item) {
        if (item instanceof ItemMoney itemMoney)
            return toMoneyPartialDTO(itemMoney);
        else if (item instanceof ItemTrash itemTrash)
            return toTrashPartialDTO(itemTrash);
        else throw new ItemNotTypeException();
    }

    @Mapping(constant = ItemTypes.Constants.MONEY_NAME, target = "type")
    ItemMoneyPartialResponseDto toMoneyPartialDTO(ItemMoney itemMoney);

    @Mapping(constant = ItemTypes.Constants.TRASH_NAME, target = "type")
    ItemTrashPartialResponseDto toTrashPartialDTO(ItemTrash itemTrash);

    @Mappings({
            @Mapping(source = "name.name", target = "name"),
            @Mapping(expression = "java(moneyMapper.toMoney(moneyType, itemMoney.getCost()))", target = "cost")
    })
    ItemMoney toItemMoney(ItemNameRequestDto name, MoneyType moneyType, ItemMoneyCreateRequestDto itemMoney);

    ItemTrash toItemTrash(ItemNameRequestDto name, ItemTrashCreateRequestDto itemTrash);

    ItemIdResponseDto toIdDTO(Item item);

    String toName(ItemNameRequestDto name);

    ItemNameRequestDto toNameDTO(Item item);
}
