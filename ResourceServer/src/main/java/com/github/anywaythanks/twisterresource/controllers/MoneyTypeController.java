package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterMoneyTypeService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/money/type")
public class MoneyTypeController {
    private final RegisterMoneyTypeService registerMoneyTypeService;
    private final MoneyTypeInformationService moneyTypeInformationService;

    public MoneyTypeController(RegisterMoneyTypeService registerMoneyTypeService,
                               MoneyTypeInformationService moneyTypeInformationService) {
        this.registerMoneyTypeService = registerMoneyTypeService;
        this.moneyTypeInformationService = moneyTypeInformationService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public MoneyDTO.Type.Response.Partial putMoneyType(
            @Valid @PathVariable MoneyDTO.Type.Request.Name name,
            @RequestBody @Valid MoneyDTO.Type.Request.Create create) {
        return registerMoneyTypeService.merge(name, create);
    }

    @GetMapping
    @Transactional
    public List<MoneyDTO.Type.Response.Partial> listMoneyTypes() {
        return moneyTypeInformationService.listPartial();
    }

    @GetMapping("/{name}")
    @Transactional
    public MoneyDTO.Type.Response.Partial getMoneyType(
            @Valid @PathVariable MoneyDTO.Type.Request.Name name) {
        return moneyTypeInformationService.getPartial(name);
    }
}