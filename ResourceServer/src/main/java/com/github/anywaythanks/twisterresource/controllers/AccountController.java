package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.account.AccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.services.AccountInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterAccountService;
import com.github.anywaythanks.twisterresource.services.TransferMoneyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/general/{name}/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final TransferMoneyService transferMoneyService;
    private final RegisterAccountService registerAccountService;
    private final AccountInformationService accountInformationService;

    @PostMapping(path = "/{sourceNumber}/transfer/{recipientNumber}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void transfer(@Valid @PathVariable GeneralAccountNameRequestDto name,
                         @Valid @PathVariable AccountNumberRequestDto sourceNumber,
                         @Valid @PathVariable AccountNumberRequestDto recipientNumber,
                         @Valid @RequestBody MoneyCreateRequestDto value) {
        transferMoneyService.transfer(name, sourceNumber, recipientNumber, value);
    }

    @PutMapping(path = "/{number}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountPartialResponseDto register(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                              @Valid @PathVariable AccountNumberRequestDto number,
                                              @Valid @RequestBody AccountCreateRequestDto requestAccount) {
        return registerAccountService.merge(name, number, requestAccount);
    }

    @PostMapping(headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountPartialResponseDto> register(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                                              @Valid @RequestBody AccountCreateRequestDto requestAccount) {
        var account = registerAccountService.register(name, requestAccount);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{number}")
                .buildAndExpand(account.getNumber()).toUri()).body(account);
    }

    @GetMapping
    public List<AccountPartialResponseDto> listAccounts(@Valid @PathVariable GeneralAccountNameRequestDto name) {
        return accountInformationService.listPartial(name);
    }

    @GetMapping("/{number}")
    public AccountPartialResponseDto info(@Valid @PathVariable GeneralAccountNameRequestDto name,
                                          @Valid @PathVariable AccountNumberRequestDto number) {
        return accountInformationService.getPartial(name, number);
    }
}
