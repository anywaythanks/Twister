package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/money/type")
public class PublicMoneyTypeController {
    private final MoneyTypeInformationService moneyTypeInformationService;

    public PublicMoneyTypeController(MoneyTypeInformationService moneyTypeInformationService) {
        this.moneyTypeInformationService = moneyTypeInformationService;
    }

    @GetMapping
    @Transactional
    public List<MoneyDTO.Type.Response.Partial> listMoneyTypes() {
        return moneyTypeInformationService.listPartial();
    }

    @GetMapping("/{name}")
    @Transactional
    public MoneyDTO.Type.Response.Partial getMoneyType(@Valid @PathVariable MoneyDTO.Type.Request.Name name) {
        return moneyTypeInformationService.getPartial(name);
    }
}
