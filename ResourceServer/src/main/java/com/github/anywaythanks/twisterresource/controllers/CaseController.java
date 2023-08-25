package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
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
    public CaseDTO.Response.Partial putCase(@Valid @PathVariable CaseDTO.Request.Name name,
                                            @Valid @RequestBody CaseDTO.Request.Create requestCase) {
        return registerCaseService.merge(name, requestCase);
    }

    @GetMapping("/{name}")
    public CaseDTO.Response.Partial info(@Valid @PathVariable CaseDTO.Request.Name name) {
        return caseInformationService.getPartial(name);
    }
}