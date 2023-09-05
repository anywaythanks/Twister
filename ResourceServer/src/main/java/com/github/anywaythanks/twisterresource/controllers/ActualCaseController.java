package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseItemsPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.CaseActualInformationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/general/{name}/actual_case")
@RequiredArgsConstructor
public class ActualCaseController {
    private final CaseActualInformationService caseActualInformationService;

    @GetMapping
    public CasePagePartialResponseDto listCase(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                               @Valid @PositiveOrZero @RequestParam(defaultValue = "0") Integer page,
                                               @Valid @Size(min = 1, max = 50) @RequestParam(defaultValue = "5") Integer size) {
        return caseActualInformationService.getPage(page, size, name);
    }

    @GetMapping("/{caseName}")
    public CaseItemsPartialResponseDto info(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                            @Valid @PathVariable CaseNameRequestDto caseName) {
        return caseActualInformationService.getPartialItems(name, caseName);
    }
}
