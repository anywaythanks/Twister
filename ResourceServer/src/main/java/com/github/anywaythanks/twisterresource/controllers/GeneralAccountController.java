package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.UserPrincipal;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
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
    public GeneralAccountDTO.Response.Partial register(@AuthenticationPrincipal UserPrincipal user,
                                                       @Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                                       @Valid @RequestBody GeneralAccountDTO.Request.Create requestAccount) {
        return registerGeneralAccountService.merge(user, name, requestAccount);
    }

    @PostMapping(headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralAccountDTO.Response.Partial> register(@AuthenticationPrincipal UserPrincipal user,
                                                                       @Valid @RequestBody GeneralAccountDTO.Request.Create requestAccount) {
        var account = registerGeneralAccountService.register(user, requestAccount);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{name}")
                .buildAndExpand(account.getName()).toUri()).body(account);
    }

    @GetMapping("/{name}")
    public GeneralAccountDTO.Response.Partial info(@Valid @PathVariable GeneralAccountDTO.Request.Name name) {
        return generalAccountInformationService.getPartial(name);
    }


    @GetMapping
    public GeneralAccountDTO.Response.Name name(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return generalAccountInformationService.getName(userPrincipal);
    }
}