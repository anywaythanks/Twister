package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NoSellingItemException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.SellingItem;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.services.InventoryInformationService;
import com.github.anywaythanks.twisterresource.services.SellService;
import com.github.anywaythanks.twisterresource.services.TransferItemService;
import com.github.anywaythanks.twisterresource.services.TransferMoneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class SellServiceImpl implements SellService {
    private final TransferMoneyService transferMoneyService;
    private final TransferItemService transferItemService;
    private final MoneyMapper moneyMapper;
    private final SlotMapper slotMapper;
    private final ItemMapper itemMapper;
    private final InventoryInformationService inventoryInformationService;
    private final ItemRepository itemRepository;

    public SellServiceImpl(TransferMoneyService transferMoneyService,
                           TransferItemService transferItemService,
                           MoneyMapper moneyMapper,
                           SlotMapper slotMapper,
                           ItemMapper itemMapper,
                           InventoryInformationService inventoryInformationService,
                           ItemRepository itemRepository) {
        this.transferMoneyService = transferMoneyService;
        this.transferItemService = transferItemService;
        this.moneyMapper = moneyMapper;
        this.slotMapper = slotMapper;
        this.itemMapper = itemMapper;
        this.inventoryInformationService = inventoryInformationService;
        this.itemRepository = itemRepository;
    }

    public void sell(GeneralAccountNameRequestDto name,
                     InventoryNameRequestDto nameInventory,
                     ItemNameRequestDto nameItem,
                     AccountNumberRequestDto number,
                     SlotQuantityRequestDto quantity) {
        var slot = inventoryInformationService.getSlotId(name, nameInventory, nameItem);
        var item = itemRepository.findById(itemMapper.toId(slot.getItem())).orElseThrow(NotFoundException::new);
        if (item instanceof SellingItem sellingItem) {
            transferItemService.remove(name, nameInventory, slotMapper.toTransfer(quantity, slot));
            transferMoneyService.debit(number, moneyMapper.toRequest(sellingItem.getCost().multiply(BigDecimal.valueOf(quantity.getQuantity()))));
        } else throw new NoSellingItemException();
    }
}
