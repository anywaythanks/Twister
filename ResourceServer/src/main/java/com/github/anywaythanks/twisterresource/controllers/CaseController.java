package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseItemsPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.CaseInformationService;
import com.github.anywaythanks.twisterresource.services.managers.CasePutService;
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
    private final CasePutService casePutService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CasePartialResponseDto putCase(@Valid @PathVariable CaseNameRequestDto name,
                                          @Valid @RequestBody CaseCreateRequestDto requestCase) {
        return casePutService.put(name, requestCase);
    }

    @GetMapping("/{name}")
    public CaseItemsPartialResponseDto info(@Valid @PathVariable CaseNameRequestDto name) {
        return caseInformationService.getPartialItems(name);
    }
}