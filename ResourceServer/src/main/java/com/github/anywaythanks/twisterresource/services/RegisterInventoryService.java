package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.InventoryNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterInventoryService {
    private final GeneralAccountInformationService generalAccountInformationService;
    public final InventoryNameRepository inventoryNameRepository;
    private final GeneralAccountRepository generalAccountRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryPartialResponseDto register(GeneralAccountNameRequestDto name) {
        var generalAccount = generalAccountRepository
                .findById(generalAccountInformationService.getId(name).getId())
                .orElseThrow(NotFoundException::new);
        var pName = inventoryNameRepository.save(new InventoryName());
        var inventory = new Inventory(pName);
        generalAccount.getInventories().put(inventory.getName(), inventory);
        return inventoryMapper.toPartialDTO(generalAccount.getInventories().get(pName));
    }
}
