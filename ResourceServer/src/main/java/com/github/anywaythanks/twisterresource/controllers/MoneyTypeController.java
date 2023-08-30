package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterMoneyTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/money/type")
@RequiredArgsConstructor
public class MoneyTypeController {
    private final RegisterMoneyTypeService registerMoneyTypeService;
    private final MoneyTypeInformationService moneyTypeInformationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public MoneyTypePartialResponseDto putMoneyType(
            @Valid @PathVariable MoneyTypeNameRequestDto name,
            @RequestBody @Valid MoneyTypeCreateRequestDto create) {
        return registerMoneyTypeService.merge(name, create);
    }

    @GetMapping
    @Transactional
    public List<MoneyTypePartialResponseDto> listMoneyTypes() {
        return moneyTypeInformationService.getPartials();
    }

    @GetMapping("/{name}")
    @Transactional
    public MoneyTypePartialResponseDto getMoneyType(
            @Valid @PathVariable MoneyTypeNameRequestDto name) {
        return moneyTypeInformationService.getPartial(name);
    }
}
