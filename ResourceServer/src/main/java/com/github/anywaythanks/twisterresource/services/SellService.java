package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NoSellingItemException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.SlotMapper;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class SellService {
    private final TransferMoneyService transferMoneyService;
    private final TransferItemService transferItemService;
    private final MoneyMapper moneyMapper;
    private final SlotMapper slotMapper;
    private final ItemMapper itemMapper;
    private final AccountInventoryInformationService accountInventoryInformationService;
    private final ItemRepository itemRepository;

    public SellService(TransferMoneyService transferMoneyService,
                       TransferItemService transferItemService,
                       MoneyMapper moneyMapper,
                       SlotMapper slotMapper,
                       ItemMapper itemMapper,
                       AccountInventoryInformationService accountInventoryInformationService,
                       ItemRepository itemRepository) {
        this.transferMoneyService = transferMoneyService;
        this.transferItemService = transferItemService;
        this.moneyMapper = moneyMapper;
        this.slotMapper = slotMapper;
        this.itemMapper = itemMapper;
        this.accountInventoryInformationService = accountInventoryInformationService;
        this.itemRepository = itemRepository;
    }

    public void sell(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Number number, ItemDTO.Request.Name nameItem,
                     SlotDTO.Request.Quantity quantity) {
        var slot = accountInventoryInformationService.getFull(name, number, nameItem);
        var item = itemRepository.findById(itemMapper.toId(slot.getItem())).orElseThrow(NotFoundException::new);
        if (item instanceof SellingItem sellingItem) {
            transferItemService.remove(name, number, slotMapper.toTransfer(quantity, slot));
            transferMoneyService.debit(number, moneyMapper.toRequest(sellingItem.getCost().multiply(BigDecimal.valueOf(quantity.getQuantity()))));
        } else throw new NoSellingItemException();
    }
}