package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.ItemMapper;
import com.github.anywaythanks.twisterresource.services.ItemInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterItemService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final RegisterItemService registerItemService;
    private final ItemInformationService itemInformationService;

    public ItemController(RegisterItemService registerItemService,
                          ItemInformationService itemInformationService) {
        this.registerItemService = registerItemService;
        this.itemInformationService = itemInformationService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO.Response.Partial put(
            @Valid @PathVariable ItemDTO.Request.Name name,
            @Valid @RequestBody ItemDTO.Request.CreateItem requestItem) {
        return registerItemService.merge(name, requestItem);
    }

    @GetMapping
    public List<ItemDTO.Response.Partial> listItems() {
        return itemInformationService.listPartial();
    }

    @GetMapping("/{name}")
    public ItemDTO.Response.Partial get(
            @Valid @PathVariable ItemDTO.Request.Name name) {
        return itemInformationService.getPartial(name);
    }
}
