package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.services.InventoryInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterInventoryService;
import com.github.anywaythanks.twisterresource.services.SellService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/general/{name}/inventory")
public class InventoryController {
    private final SellService sellService;
    private final RegisterInventoryService registerInventoryService;
    private final InventoryInformationService inventoryInformationService;

    public InventoryController(SellService sellService,
                               RegisterInventoryService registerInventoryService,
                               InventoryInformationService inventoryInformationService) {
        this.sellService = sellService;
        this.registerInventoryService = registerInventoryService;
        this.inventoryInformationService = inventoryInformationService;
    }

    @PostMapping(path = "/{nameInventory}/{item}/sell/{accountNumber}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sell(@Valid @PathVariable GeneralAccountNameRequestDto name,
                     @Valid @PathVariable InventoryNameRequestDto nameInventory,
                     @Valid @PathVariable AccountNumberRequestDto accountNumber,
                     @Valid @PathVariable ItemNameRequestDto item,
                     @Valid @RequestBody SlotQuantityRequestDto quantity) {
        sellService.sell(name, nameInventory, item, accountNumber, quantity);
    }

    @PostMapping
    public InventoryPartialResponseDto register(@Valid @PathVariable GeneralAccountNameRequestDto name) {
        return registerInventoryService.register(name);
    }

    @GetMapping("/{nameInventory}")
    public InventoryPartialResponseDto info(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                            @Valid @PathVariable InventoryNameRequestDto nameInventory) {
        return inventoryInformationService.getPartial(name, nameInventory);
    }

    @GetMapping("/{nameInventory}/{item}")
    public SlotPartialResponseDto infoSlot(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                           @Valid @PathVariable InventoryNameRequestDto nameInventory,
                                           @Valid @PathVariable ItemNameRequestDto item) {
        return inventoryInformationService.getSlotPartial(name, nameInventory, item);
    }

    @GetMapping
    public List<InventoryNameResponseDto> names(@Valid @PathVariable GeneralAccountNameRequestDto name) {
        return inventoryInformationService.names(name);
    }
}
