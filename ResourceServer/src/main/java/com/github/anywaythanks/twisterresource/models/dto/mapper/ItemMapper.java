package com.github.anywaythanks.twisterresource.models.dto.mappers;

import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.item.*;


public interface ItemMapper {
     ItemPartialResponseDto toPartialDTO(Item item);

     ItemMoney toItemMoney(ItemNameRequestDto name, MoneyType moneyType, ItemMoneyCreateRequestDto itemMoney);

     ItemTrash toItemTrash(ItemNameRequestDto name, ItemTrashCreateRequestDto itemMoney);

     ItemIdResponseDto toIdDTO(Item item);

     String toName(ItemNameRequestDto name);

     ItemNameRequestDto toNameDTO(Item item);

     Long toId(ItemIdResponseDto id);
}
