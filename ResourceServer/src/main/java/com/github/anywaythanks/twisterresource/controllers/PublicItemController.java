package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.ItemInformationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/item")
@RequiredArgsConstructor
public class PublicItemController {
    private final ItemInformationService itemInformationService;

    @GetMapping
    public List<ItemPartialResponseDto> listItems() {
        return itemInformationService.getPartials();
    }

    @GetMapping("/{name}")
    public ItemPartialResponseDto get(
            @Valid @PathVariable ItemNameRequestDto name) {
        return itemInformationService.getPartial(name);
    }
}
