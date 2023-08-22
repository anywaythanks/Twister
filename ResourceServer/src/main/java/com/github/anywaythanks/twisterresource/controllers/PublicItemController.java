package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;
import com.github.anywaythanks.twisterresource.services.ItemInformationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/item")
public class PublicItemController {
    private final ItemInformationService itemInformationService;

    public PublicItemController(ItemInformationService itemInformationService) {
        this.itemInformationService = itemInformationService;
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
