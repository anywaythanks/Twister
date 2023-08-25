package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import com.github.anywaythanks.twisterresource.services.TwistService;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/general/{name}/accounts/{number}/twist/{nameInventory}")
public class TwisterController {

    private final TwistService twistService;

    public TwisterController(TwistService twistService) {
        this.twistService = twistService;
    }

    @PostMapping(path = "/{caseName}")
    @Transactional
    public TwistPartialResponseDto twist(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                         @Valid @PathVariable AccountNumberRequestDto number,
                                         @Valid @PathVariable InventoryNameRequestDto nameInventory,
                                         @Valid @PathVariable CaseNameRequestDto caseName) throws NoSuchAlgorithmException {
        return twistService.twist(name, nameInventory, number, caseName);
    }
}