package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.ItemInformationService;
import com.github.anywaythanks.twisterresource.services.managers.ItemRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRegisterService registerItemService;
    private final ItemInformationService itemInformationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemPartialResponseDto put(
            @Valid @PathVariable ItemNameRequestDto name,
            @Valid @RequestBody ItemCreateRequestDto requestItem) {
        return registerItemService.merge(name, requestItem);
    }

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
