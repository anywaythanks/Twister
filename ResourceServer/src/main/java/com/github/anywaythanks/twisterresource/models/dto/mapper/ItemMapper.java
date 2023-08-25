package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;


public interface ItemMapper {
     ItemDTO.Response.Partial toPartialDTO(Item item);

     ItemMoney toItemMoney(ItemDTO.Request.Name name, MoneyType moneyType, ItemDTO.Request.CreateMoney itemMoney);

     ItemTrash toItemTrash(ItemDTO.Request.Name name, ItemDTO.Request.CreateTrash itemMoney);

     ItemDTO.Response.Id toIdDTO(Item item);

     String toName(ItemDTO.Request.Name name);

     ItemDTO.Request.Name toNameDTO(Item item);

     Long toId(ItemDTO.Response.Id id);
}
