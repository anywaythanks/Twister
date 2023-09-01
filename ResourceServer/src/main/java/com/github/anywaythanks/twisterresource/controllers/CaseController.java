package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialItemsResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.CaseInformationService;
import com.github.anywaythanks.twisterresource.services.managers.CaseRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/case")
@RequiredArgsConstructor
public class CaseController {
    private final CaseInformationService caseInformationService;
    private final CaseRegisterService registerCaseService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CasePartialItemsResponseDto putCase(@Valid @PathVariable CaseNameRequestDto name,
                                               @Valid @RequestBody CaseCreateRequestDto requestCase) {
        return registerCaseService.merge(name, requestCase);
    }

    @GetMapping("/{name}")
    public CasePartialItemsResponseDto info(@Valid @PathVariable CaseNameRequestDto name) {
        return caseInformationService.getPartialItems(name);
    }
}