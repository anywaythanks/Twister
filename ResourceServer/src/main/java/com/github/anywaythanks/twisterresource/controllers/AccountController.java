package com.github.anywaythanks.twisterresource.controllers;

import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyMapper;
import com.github.anywaythanks.twisterresource.services.*;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/general/{name}/accounts")
public class AccountController {
    private final TransferMoneyService transferMoneyService;
    private final RegisterAccountService registerAccountService;
    private final AccountInformationService accountInformationService;

    public AccountController(TransferMoneyService transferMoneyService,
                             RegisterAccountService registerAccountService,
                             AccountInformationService accountInformationService) {
        this.registerAccountService = registerAccountService;
        this.transferMoneyService = transferMoneyService;
        this.accountInformationService = accountInformationService;
    }

    @PostMapping(path = "/{sourceNumber}/transfer/{recipientNumber}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void transfer(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                         @Valid @PathVariable AccountDTO.Request.Number sourceNumber,
                         @Valid @PathVariable AccountDTO.Request.Number recipientNumber,
                         @Valid @RequestBody MoneyDTO.Request.Create value) {
        transferMoneyService.transfer(name, sourceNumber, recipientNumber, value);
    }

    @PutMapping(path = "/{number}", headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountDTO.Response.Partial register(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                                @Valid @PathVariable AccountDTO.Request.Number number,
                                                @Valid @RequestBody AccountDTO.Request.Create requestAccount) {
        return registerAccountService.merge(name, number, requestAccount);
    }

    @PostMapping(headers = "content-type=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO.Response.Partial> register(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                                                @Valid @RequestBody AccountDTO.Request.Create requestAccount) {
        var account = registerAccountService.register(name, requestAccount);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{number}")
                .buildAndExpand(account.getNumber()).toUri()).body(account);
    }

    @GetMapping
    public List<AccountDTO.Response.Partial> listAccounts(@Valid @PathVariable GeneralAccountDTO.Request.Name name) {
        return accountInformationService.listPartial(name);
    }

    @GetMapping("/{number}")
    public AccountDTO.Response.Partial info(@Valid @PathVariable GeneralAccountDTO.Request.Name name,
                                            @Valid @PathVariable AccountDTO.Request.Number number) {
        return accountInformationService.getPartial(name, number);
    }
}
