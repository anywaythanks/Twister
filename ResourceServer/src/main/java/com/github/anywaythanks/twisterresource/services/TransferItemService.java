package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.AccountSlot;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.ItemMapper;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransferItemService {
    private final AccountMapper accountMapper;
    private final AccountInformationService accountInformationService;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final AccountRepository accountRepository;

    public TransferItemService(AccountMapper accountMapper,
                               AccountInformationService accountInformationService,
                               ItemRepository itemRepository,
                               ItemMapper itemMapper,
                               AccountRepository accountRepository) {
        this.accountMapper = accountMapper;
        this.accountInformationService = accountInformationService;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;

        this.accountRepository = accountRepository;
    }

    public void add(AccountDTO.Request.Number number, SlotDTO.Request.Transfer slotTransfer) {
        final var account = accountRepository.findById(accountMapper.toId(accountInformationService.getDebit(number)))
                .orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(itemMapper.toId(slotTransfer.getItem()))
                .orElseThrow(NotFoundException::new);
        account.getAccountSlotMap().putIfAbsent(item, new AccountSlot<>(item, 0));
        var slot = account.getAccountSlotMap().get(item);
        slot.addItems(item, slotTransfer.getQuantity());
    }

    public void remove(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Number number,
                       SlotDTO.Request.Transfer slotTransfer) {
        final var account = accountRepository.findById(accountMapper.toId(accountInformationService.getCredit(name, number)))
                .orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(itemMapper.toId(slotTransfer.getItem()))
                .orElseThrow(NotFoundException::new);
        account.getAccountSlotMap().putIfAbsent(item, new AccountSlot<>(item, 0));
        var slot = account.getAccountSlotMap().get(item);
        slot.removeItems(item, slotTransfer.getQuantity());
    }

    public void transfer(GeneralAccountDTO.Request.Name name,
                         AccountDTO.Request.Number accountFrom, AccountDTO.Request.Number accountTo,
                         SlotDTO.Request.Transfer slotTransfer) {
        remove(name, accountFrom, slotTransfer);
        add(accountTo, slotTransfer);
    }
}
