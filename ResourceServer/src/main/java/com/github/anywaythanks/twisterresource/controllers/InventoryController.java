package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.*;
import com.github.anywaythanks.twisterresource.services.*;
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
    public void sell(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                     @Valid @PathVariable InventoryDTO.Request.Name nameInventory,
                     @Valid @PathVariable AccountDTO.Request.Number accountNumber,
                     @Valid @PathVariable ItemDTO.Request.Name item,
                     @Valid @RequestBody SlotDTO.Request.Quantity quantity) {
        sellService.sell(name, nameInventory, item, accountNumber, quantity);
    }

    @PostMapping
    public InventoryDTO.Response.Partial register(@Valid @PathVariable GeneralAccountDTO.Request.Name name) {
        return registerInventoryService.register(name);
    }

    @GetMapping("/{nameInventory}")
    public InventoryDTO.Response.Partial info(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                              @Valid @PathVariable InventoryDTO.Request.Name nameInventory) {
        return inventoryInformationService.getPartial(name, nameInventory);
    }

    @GetMapping("/{nameInventory}/{item}")
    public SlotDTO.Response.Partial infoSlot(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                             @Valid @PathVariable InventoryDTO.Request.Name nameInventory,
                                             @Valid @PathVariable ItemDTO.Request.Name item) {
        return inventoryInformationService.getSlotPartial(name, nameInventory, item);
    }

    @GetMapping
    public List<InventoryDTO.Response.Name> names(@Valid @PathVariable GeneralAccountDTO.Request.Name name) {
        return inventoryInformationService.names(name);
    }
}
