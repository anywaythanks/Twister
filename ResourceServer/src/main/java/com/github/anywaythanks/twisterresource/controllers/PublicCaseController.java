package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import com.github.anywaythanks.twisterresource.models.dto.PageDTO;
import com.github.anywaythanks.twisterresource.services.CaseInformationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public/case")
public class PublicCaseController {
    private final CaseInformationService caseInformationService;

    public PublicCaseController(CaseInformationService caseInformationService) {
        this.caseInformationService = caseInformationService;
    }

    @GetMapping
    public PageDTO.Response.Partial<CaseDTO.Response.LightPartialWithoutCooldown> listCase(
            @Valid @PositiveOrZero @RequestParam(defaultValue = "0") Integer page,
            @Valid @Size(min = 1, max = 50) @RequestParam(defaultValue = "5") Integer size) {
        return caseInformationService.getPageWithoutCooldown(page, size);
    }

    @GetMapping("/{name}")
    public CaseDTO.Response.PartialWithoutCooldown info(@Valid @PathVariable CaseDTO.Request.Name name) {
        return caseInformationService.getPartialWithoutCooldown(name);
    }
}
