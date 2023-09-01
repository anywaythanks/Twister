package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNicknameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPublicResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.GeneralAccountInformationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/public/general")
@RestController
@RequiredArgsConstructor
public class PublicGeneralAccountController {
    private final GeneralAccountInformationService generalAccountInformationService;
    @RequestMapping(path = "/{name}")
    public GeneralAccountPublicResponseDto infoPublic(@Valid @PathVariable GeneralAccountNameRequestDto name) {
        return generalAccountInformationService.getPublic(name);
    }

    @GetMapping("/search")
    public GeneralAccountPublicResponseDto search(@Valid @RequestParam GeneralAccountNicknameRequestDto nickname) {
        return generalAccountInformationService.getPublic(nickname);
    }
}
