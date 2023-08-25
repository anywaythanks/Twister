package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.InventoryNameRepository;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterInventoryServiceImpl implements RegisterInventoryService {
    private final GeneralAccountInformationService generalAccountInformationService;
    public final InventoryNameRepository inventoryNameRepository;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final InventoryMapper inventoryMapper;

    public RegisterInventoryServiceImpl(GeneralAccountInformationService generalAccountInformationService,
                                        InventoryNameRepository inventoryNameRepository,
                                        GeneralAccountMapper generalAccountMapper,
                                        GeneralAccountRepository generalAccountRepository,
                                        InventoryMapper inventoryMapper) {
        this.generalAccountInformationService = generalAccountInformationService;
        this.inventoryNameRepository = inventoryNameRepository;
        this.generalAccountMapper = generalAccountMapper;
        this.generalAccountRepository = generalAccountRepository;
        this.inventoryMapper = inventoryMapper;
    }

    public InventoryPartialResponseDto register(GeneralAccountNameRequestDto name) {
        var generalAccount = generalAccountRepository
                .findById(generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var pName = inventoryNameRepository.save(new InventoryName());
        var inventory = new Inventory(pName);
        generalAccount.getInventories().put(inventory.getName(), inventory);
        return inventoryMapper.toPartialDTO(generalAccount.getInventories().get(pName));
    }
}
