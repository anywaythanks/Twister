package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterGeneralAccountService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/general")
public class GeneralAccountController {
    private final RegisterGeneralAccountService registerGeneralAccountService;
    private final GeneralAccountInformationService generalAccountInformationService;

    public GeneralAccountController(RegisterGeneralAccountService registerGeneralAccountService,
                                    GeneralAccountInformationService generalAccountInformationService) {
        this.registerGeneralAccountService = registerGeneralAccountService;
        this.generalAccountInformationService = generalAccountInformationService;
    }

    @PutMapping(path = "/{name}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralAccountPartialResponseDto register(@AuthenticationPrincipal UserPrincipal user,
                                                     @Valid @PathVariable GeneralAccountNameRequestDto name,
                                                     @Valid @RequestBody GeneralAccountCreateRequestDto requestAccount) {
        return registerGeneralAccountService.merge(user, name, requestAccount);
    }

    @PostMapping(headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralAccountPartialResponseDto> register(@AuthenticationPrincipal UserPrincipal user,
                                                                     @Valid @RequestBody GeneralAccountCreateRequestDto requestAccount) {
        var account = registerGeneralAccountService.register(user, requestAccount);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{name}")
                .buildAndExpand(account.getName()).toUri()).body(account);
    }

    @GetMapping("/{name}")
    public GeneralAccountPartialResponseDto info(@Valid @PathVariable GeneralAccountNameRequestDto name) {
        return generalAccountInformationService.getPartial(name);
    }


    @GetMapping
    public GeneralAccountNameResponseDto name(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return generalAccountInformationService.getName(userPrincipal);
    }
}