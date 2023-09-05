package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryPartialSlotsResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.services.ShopService;
import com.github.anywaythanks.twisterresource.services.managers.InventoryInformationService;
import com.github.anywaythanks.twisterresource.services.managers.InventoryRegisterService;
import com.github.anywaythanks.twisterresource.services.managers.InventorySlotInformationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/general/{name}/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final ShopService sellService;
    private final InventoryRegisterService registerInventoryService;
    private final InventoryInformationService inventoryInformationService;
    private final InventorySlotInformationService inventorySlotInformationService;

    @PostMapping(path = "/{nameInventory}/{item}/sell/{accountNumber}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sell(@Valid @PathVariable GeneralAccountNameRequestDto name,
                     @Valid @PathVariable InventoryNameRequestDto nameInventory,
                     @Valid @PathVariable AccountNumberRequestDto accountNumber,
                     @Valid @PathVariable ItemNameRequestDto item,
                     @Valid @RequestBody SlotQuantityRequestDto quantity) {
        sellService.sell(name, nameInventory, item, accountNumber, quantity);
    }

    @PostMapping
    public ResponseEntity<InventoryPartialResponseDto> register(@Valid @PathVariable GeneralAccountNameRequestDto name) {
        var inventory = registerInventoryService.register(name);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{name}")
                .buildAndExpand(inventory.getName()).toUri()).body(inventory);
    }

    @GetMapping("/{nameInventory}")
    public InventoryPartialSlotsResponseDto info(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                                 @Valid @PathVariable InventoryNameRequestDto nameInventory) {
        return inventoryInformationService.getInventoryPartial(name, nameInventory);
    }

    @GetMapping("/{nameInventory}/{item}")
    public SlotPartialResponseDto infoSlot(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                           @Valid @PathVariable InventoryNameRequestDto nameInventory,
                                           @Valid @PathVariable ItemNameRequestDto item) {
        return inventoryInformationService.getSlotPartial(name, nameInventory, item);
    }

    @GetMapping
    public List<InventoryNameResponseDto> names(@Valid @PathVariable GeneralAccountNameRequestDto name) {
        return inventoryInformationService.getInventoryNames(name);
    }
}
