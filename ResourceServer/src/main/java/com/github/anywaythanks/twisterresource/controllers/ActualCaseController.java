package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.PageDTO;
import com.github.anywaythanks.twisterresource.services.CaseActualInformationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/general/{name}/actual_case")
public class ActualCaseController {
    private final CaseActualInformationService caseActualInformationService;

    public ActualCaseController(CaseActualInformationService caseActualInformationService) {
        this.caseActualInformationService = caseActualInformationService;
    }

    @GetMapping
    public PageDTO.Response.Partial<CaseDTO.Response.LightPartial> listCase(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                                                            @Valid @PositiveOrZero  @RequestParam(defaultValue = "0") Integer page,
                                                                            @Valid @Size(min = 1, max = 50) @RequestParam(defaultValue = "5") Integer size) {
        return caseActualInformationService.getPage(page, size, name);
    }

    @GetMapping("/{caseName}")
    public CaseDTO.Response.Partial info(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                         @Valid @PathVariable CaseDTO.Request.Name caseName) {
        return caseActualInformationService.getPartial(name, caseName);
    }
}
