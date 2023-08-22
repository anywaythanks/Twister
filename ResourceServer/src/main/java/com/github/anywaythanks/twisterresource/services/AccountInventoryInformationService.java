package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.SlotMapper;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountInventoryInformationService {
    private final AccountInformationService accountInformationService;
    private final ItemRepository itemRepository;
    private final ItemInformationService itemInformationService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ItemMapper itemMapper;
    private final SlotMapper slotMapper;

    public AccountInventoryInformationService(AccountInformationService accountInformationService,
                                              ItemRepository itemRepository,
                                              ItemInformationService itemInformationService,
                                              AccountRepository accountRepository,
                                              AccountMapper accountMapper,
                                              ItemMapper itemMapper,
                                              SlotMapper slotMapper) {
        this.accountInformationService = accountInformationService;
        this.itemRepository = itemRepository;
        this.itemInformationService = itemInformationService;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.itemMapper = itemMapper;
        this.slotMapper = slotMapper;
    }

    protected Slot<?> get(GeneralAccountDTO.Request.Name name,
                        AccountDTO.Request.Number number, ItemDTO.Request.Name nameItem) {
        final var account = accountRepository.findById(accountMapper.toId(accountInformationService
                .getId(name, number))).orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(itemMapper.toId(itemInformationService.getId(nameItem)))
                .orElseThrow(NotFoundException::new);
        var slot = account.getAccountSlotMap().get(item);
        if (slot == null) throw new NotFoundException();
        return slot;
    }

    public SlotDTO.Response.Ids getFull(GeneralAccountDTO.Request.Name name,
                                        AccountDTO.Request.Number number, ItemDTO.Request.Name nameItem) {
        return slotMapper.toIdsDTO(get(name, number, nameItem));
    }

    public SlotDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name name,
                                               AccountDTO.Request.Number number, ItemDTO.Request.Name nameItem) {
        return slotMapper.toPartialDTO(get(name, number, nameItem));
    }


    public List<SlotDTO.Response.Partial> listPartial(GeneralAccountDTO.Request.Name name,
                                                      AccountDTO.Request.Number number) {
        final var account = accountRepository.findById(accountMapper.toId(accountInformationService
                .getId(name, number))).orElseThrow(NotFoundException::new);
        return account.getAccountSlotMap().values().stream().map(slotMapper::toPartialDTO).toList();
    }
}
