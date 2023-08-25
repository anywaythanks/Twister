package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
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
    public List<ItemPartialResponseDto> listItems() {
        return itemInformationService.listPartial();
    }

    @GetMapping("/{name}")
    public ItemPartialResponseDto get(
            @Valid @PathVariable ItemNameRequestDto name) {
        return itemInformationService.getPartial(name);
    }
}
