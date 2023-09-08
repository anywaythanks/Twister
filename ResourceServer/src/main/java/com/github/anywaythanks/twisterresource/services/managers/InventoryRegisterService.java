package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.InventoryNameRepository;
import com.github.anywaythanks.twisterresource.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@RegisterService
@RequiredArgsConstructor
public class InventoryRegisterService {
    private final GeneralAccountInformationService generalAccountInformationService;
    public final InventoryNameRepository inventoryNameRepository;
    private final InventoryMapper inventoryMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final InventoryRepository inventoryRepository;
    private final Clock clock;

    @Transactional
    public InventoryPartialResponseDto register(GeneralAccountNameRequestDto name) {
        GeneralAccountIdAndUuidDto accountId = generalAccountInformationService.getId(name);
        InventoryName persistenceName = inventoryNameRepository.save(InventoryName.builder().build());
        Instant now = Instant.now(clock);
        GeneralAccount generalAccount = generalAccountMapper.toAccount(accountId);
        Inventory newInventory = Inventory.builder()
                .name(persistenceName)
                .generalAccount(generalAccount)
                .createdOn(now)
                .modifiedBy(now)
                .build();
        Inventory result = inventoryRepository.save(newInventory);
        return inventoryMapper.toPartialDTO(result);
    }
}
