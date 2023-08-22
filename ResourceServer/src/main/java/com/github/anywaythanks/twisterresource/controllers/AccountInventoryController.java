package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.SlotMapper;
import com.github.anywaythanks.twisterresource.services.*;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/general/{name}/accounts/{number}/inventory")
public class AccountInventoryController {
    private final SellService sellService;
    private final AccountInventoryInformationService accountInventoryInformationService;

    public AccountInventoryController(SellService sellService,
                                      AccountInventoryInformationService accountInventoryInformationService) {
        this.sellService = sellService;
        this.accountInventoryInformationService = accountInventoryInformationService;
    }

    @PostMapping(path = "/{item}/sell", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sell(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                     @Valid @PathVariable AccountDTO.Request.Number number,
                     @Valid @PathVariable ItemDTO.Request.Name item,
                     @Valid @RequestBody SlotDTO.Request.Quantity quantity) {
        sellService.sell(name, number, item, quantity);
    }

    @GetMapping
    public List<SlotDTO.Response.Partial> list(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                               @Valid @PathVariable AccountDTO.Request.Number number) {
        return accountInventoryInformationService.listPartial(name, number);
    }

    @GetMapping("/{item}")
    public SlotDTO.Response.Partial info(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                         @Valid @PathVariable AccountDTO.Request.Number number,
                                         @Valid @PathVariable ItemDTO.Request.Name item) {
        return accountInventoryInformationService.getPartial(name, number, item);
    }
}
