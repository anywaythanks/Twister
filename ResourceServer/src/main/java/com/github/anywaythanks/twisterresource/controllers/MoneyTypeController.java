package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.managers.MoneyTypePutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/money/type")
@RequiredArgsConstructor
public class MoneyTypeController {
    private final MoneyTypePutService moneyTypePutService;
    private final MoneyTypeInformationService moneyTypeInformationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public MoneyTypePartialResponseDto putMoneyType(
            @Valid @PathVariable MoneyTypeNameRequestDto name,
            @RequestBody @Valid MoneyTypeCreateRequestDto create) {
        return moneyTypePutService.put(name, create);
    }

    @GetMapping
    @Transactional
    public Collection<MoneyTypePartialResponseDto> listMoneyTypes() {
        return moneyTypeInformationService.getPartials();
    }

    @GetMapping("/{name}")
    @Transactional
    public MoneyTypePartialResponseDto getMoneyType(
            @Valid @PathVariable MoneyTypeNameRequestDto name) {
        return moneyTypeInformationService.getPartial(name);
    }
}
