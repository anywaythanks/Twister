package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.services.CaseInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterCaseService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/case")
public class CaseController {
    private final CaseInformationService caseInformationService;
    private final RegisterCaseService registerCaseService;

    public CaseController(CaseInformationService caseInformationService,
                          RegisterCaseService registerCaseService) {
        this.caseInformationService = caseInformationService;
        this.registerCaseService = registerCaseService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CasePartialResponseDto putCase(@Valid @PathVariable CaseNameRequestDto name,
                                          @Valid @RequestBody CaseCreateRequestDto requestCase) {
        return registerCaseService.merge(name, requestCase);
    }

    @GetMapping("/{name}")
    public CasePartialResponseDto info(@Valid @PathVariable CaseNameRequestDto name) {
        return caseInformationService.getPartial(name);
    }
}