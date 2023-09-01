package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.dto.item.*;
import org.mapstruct.*;

@Mapper(config = MapstructConfig.class)
public interface ItemMapper {
    @SubclassMapping(source = ItemMoney.class, target = ItemMoneyPartialResponseDto.class)
    @SubclassMapping(source = ItemTrash.class, target = ItemTrashPartialResponseDto.class)
    ItemPartialResponseDto toPartialDTO(Item item);

    @Mapping(constant = ItemTypes.Constants.MONEY_NAME, target = "type")
    ItemMoneyPartialResponseDto toMoneyPartialDTO(ItemMoney itemMoney);

    @Mapping(constant = ItemTypes.Constants.TRASH_NAME, target = "type")
    ItemTrashPartialResponseDto toTrashPartialDTO(ItemTrash itemTrash);

    @SubclassMapping(source = ItemMoneyFullDto.class, target = ItemMoney.class)
    @SubclassMapping(source = ItemTrashFullDto.class, target = ItemTrash.class)
    Item toItem(ItemFullDto full);

    ItemMoney toItemMoney(ItemMoneyFullDto moneyFull);


    ItemTrash toItemTrash(ItemTrashFullDto trashFull);

    @InheritInverseConfiguration
    ItemFullDto toFullItem(Item item);

    ItemMoneyFullDto toItemMoney(ItemMoney itemMoney);

    ItemTrashFullDto toItemTrash(ItemTrash itemTrashFull);

    @SubclassMapping(source = ItemMoneyIdDto.class, target = ItemMoney.class)
    @SubclassMapping(source = ItemTrashIdDto.class, target = ItemTrash.class)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "visibleName", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    Item toItem(ItemIdDto id);

    @Mapping(source = "id.id", target = "id")
    @Mapping(target = "cost", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "visibleName", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    ItemMoney toItemMoney(ItemMoneyIdDto id);

    @Mapping(source = "id.id", target = "id")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "visibleName", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    ItemTrash toItemTrash(ItemTrashIdDto id);

    @InheritInverseConfiguration
    ItemIdDto toIdDTO(Item item);

    ItemMoneyIdDto toIdMoneyDto(ItemMoney item);

    ItemTrashIdDto toIdTrashDto(ItemTrash item);

    String toName(ItemNameRequestDto name);

    ItemNameRequestDto toNameDTO(Item item);

    @ObjectFactory
    default ItemPartialResponseDto getItemPartialResponseDto(Item item) {
        throw new ItemNotTypeException();
    }

    @ObjectFactory
    default ItemIdDto getItemIdDto(Item item) {
        throw new ItemNotTypeException();
    }

    @ObjectFactory
    default Item getItemDto(ItemIdDto item) {
        throw new ItemNotTypeException();
    }

    @ObjectFactory
    default ItemFullDto getItemFullDto(Item item) {
        throw new ItemNotTypeException();
    }

    @ObjectFactory
    default Item getItemDto(ItemFullDto item) {
        throw new ItemNotTypeException();
    }
}
