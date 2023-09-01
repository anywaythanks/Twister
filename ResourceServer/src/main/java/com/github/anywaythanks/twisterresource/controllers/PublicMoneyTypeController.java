package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.MoneyTypeInformationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/money/type")
@RequiredArgsConstructor
public class PublicMoneyTypeController {
    private final MoneyTypeInformationService moneyTypeInformationService;

    @GetMapping
    @Transactional
    public List<MoneyTypePartialResponseDto> listMoneyTypes() {
        return moneyTypeInformationService.getPartials();
    }

    @GetMapping("/{name}")
    @Transactional
    public MoneyTypePartialResponseDto getMoneyType(@Valid @PathVariable MoneyTypeNameRequestDto name) {
        return moneyTypeInformationService.getPartial(name);
    }
}
