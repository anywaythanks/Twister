package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.TwistDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.CaseMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.TwistMapper;
import com.github.anywaythanks.twisterresource.services.AccountInformationService;
import com.github.anywaythanks.twisterresource.services.CaseActualInformationService;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import com.github.anywaythanks.twisterresource.services.TwistService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/general/{name}/accounts/{number}/twist")
public class TwisterController {

    private final TwistService twistService;

    public TwisterController(TwistService twistService) {
        this.twistService = twistService;
    }

    @PostMapping(path = "/{caseName}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public TwistDTO.Response.Partial twist(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                           @Valid @PathVariable AccountDTO.Request.Number number,
                                           @Valid @PathVariable CaseDTO.Request.Name caseName,
                                           @Valid @RequestBody TwistDTO.Request.Create requestTwist) throws NoSuchAlgorithmException {
        return twistService.twist(name, number, caseName);
    }
}