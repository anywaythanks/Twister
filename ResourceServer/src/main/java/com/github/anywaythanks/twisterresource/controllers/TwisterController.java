package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.*;
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
    public TwistDTO.Response.Partial twist(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                           @Valid @PathVariable AccountDTO.Request.Number number,
                                           @Valid @PathVariable InventoryDTO.Request.Name nameInventory,
                                           @Valid @PathVariable CaseDTO.Request.Name caseName) throws NoSuchAlgorithmException {
        return twistService.twist(name, nameInventory, number, caseName);
    }
}