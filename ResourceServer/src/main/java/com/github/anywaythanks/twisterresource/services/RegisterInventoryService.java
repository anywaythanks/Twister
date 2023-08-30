package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.InventoryNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RegisterInventoryService {
    private final GeneralAccountInformationService generalAccountInformationService;
    public final InventoryNameRepository inventoryNameRepository;
    private final GeneralAccountRepository generalAccountRepository;
    private final InventoryMapper inventoryMapper;

    @Transactional
    public InventoryPartialResponseDto register(GeneralAccountNameRequestDto name) {
        GeneralAccountIdResponseDto accountId = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(accountId.getId())
                .orElseThrow(NotFoundException::new);
        InventoryName persistenceName = inventoryNameRepository.save(new InventoryName());
        Instant now = Instant.now();
        Inventory newInventory = new Inventory(persistenceName, now, now, generalAccount);
        generalAccount.getInventories().add(newInventory);
        return inventoryMapper.toPartialDTO(newInventory);
    }
}
