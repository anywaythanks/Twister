package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialItemsWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CaseWithoutCooldownPagePartialResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.CaseInformationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public/case")
@RequiredArgsConstructor
public class PublicCaseController {
    private final CaseInformationService caseInformationService;

    @GetMapping
    public CaseWithoutCooldownPagePartialResponseDto listCase(
            @Valid @PositiveOrZero @RequestParam(defaultValue = "0") Integer page,
            @Valid @Size(min = 1, max = 50) @RequestParam(defaultValue = "5") Integer size) {
        return caseInformationService.getPageWithoutCooldown(page, size);
    }

    @GetMapping("/{name}")
    public CasePartialItemsWithoutCooldownResponseDto info(@Valid @PathVariable CaseNameRequestDto name) {
        return caseInformationService.getPartialWithoutCooldown(name);
    }
}
