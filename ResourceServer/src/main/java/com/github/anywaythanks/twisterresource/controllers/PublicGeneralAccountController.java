package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/public/general")
@RestController
public class PublicGeneralAccountController {
    private final GeneralAccountInformationService generalAccountInformationService;

    public PublicGeneralAccountController(GeneralAccountInformationService generalAccountInformationService) {
        this.generalAccountInformationService = generalAccountInformationService;
    }

    @RequestMapping(path = "/{name}")
    public GeneralAccountDTO.Response.Public infoPublic(@Valid @PathVariable GeneralAccountDTO.Request.Name name) {
        return generalAccountInformationService.getPublic(name);
    }

    @GetMapping("/search")
    public GeneralAccountDTO.Response.Public search(@Valid @RequestParam GeneralAccountDTO.Request.Nickname nickname) {
        return generalAccountInformationService.getPublic(nickname);
    }
}
