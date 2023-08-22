package com.example.twisterclient.web;

import com.example.twisterclient.models.Account;
import com.example.twisterclient.models.GeneralAccount;
import com.example.twisterclient.models.GeneralAccountName;
import com.example.twisterclient.models.Money;
import com.example.twisterclient.models.dto.AccountDTO;
import com.example.twisterclient.models.dto.GeneralAccountDTO;
import com.example.twisterclient.services.AccountsService;
import com.example.twisterclient.services.GeneralAccountSession;
import com.example.twisterclient.services.ModelPlaceholderService;
import jakarta.validation.Valid;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Controller
@RequestMapping("/")
public class AccountController {
    private final GeneralAccountSession generalAccountSession;
    private final AccountsService accountsService;
    private final WebClient webClient;
    private final ModelPlaceholderService modelPlaceholderService;

    public AccountController(GeneralAccountSession generalAccountSession,
                             AccountsService accountsService,
                             WebClient webClient,
                             ModelPlaceholderService modelPlaceholderService) {
        this.generalAccountSession = generalAccountSession;
        this.accountsService = accountsService;
        this.webClient = webClient;
        this.modelPlaceholderService = modelPlaceholderService;
    }

    @GetMapping("{name}")
    public String info(Model model,
                       @AuthenticationPrincipal DefaultOidcUser user,
                       @PathVariable @Valid GeneralAccountName name) {
        if (name.equals(generalAccountSession.getName())) {
            return "forward:me";
        }
        modelPlaceholderService.load(model, user);
        model.addAttribute("account", webClient
                .get()
                .uri("/api/public/general/{name}", name.getName())
                .attributes(clientRegistrationId("keycloak-confidential-user"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<GeneralAccount>() {
                })
                .block());
        return "publicInfoUser";
    }

    @GetMapping("me")
    public String me(Model model,
                     @AuthenticationPrincipal DefaultOidcUser user) {
        if (generalAccountSession.getName() == null) return "redirect:setting";
        modelPlaceholderService.load(model, user);
        return "personalInfoUser";
    }

    @GetMapping("setting")
    public String generalSetting(Model model,
                                 @AuthenticationPrincipal DefaultOidcUser user) {
        modelPlaceholderService.load(model, user);
        return "generalSetting";
    }

    @PostMapping("setting")
    public String generalSetting(GeneralAccountDTO.Request.Setting accountSetting) {
        if (generalAccountSession.getName() == null) {
            webClient
                    .post()
                    .uri("/api/general")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(accountSetting)
                    .attributes(clientRegistrationId("keycloak-confidential-user"))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<GeneralAccount>() {
                    })
                    .block();
        } else {
            webClient
                    .put()
                    .uri("/api/general/{name}", generalAccountSession.getName().getName())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(accountSetting)
                    .attributes(clientRegistrationId("keycloak-confidential-user"))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<GeneralAccount>() {
                    })
                    .block();
        }
        var moneyTypes = webClient
                .get()
                .uri("/api/public/money/type")
                .attributes(clientRegistrationId("keycloak-confidential-user"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Money.Type>>() {
                })
                .block();
        var accounts = accountsService.load();
        if (moneyTypes != null && accounts != null)
            for (var moneyType : moneyTypes) {
                if (accounts.get(moneyType) == null) {
                    webClient
                            .post()
                            .uri("/api/general/{name}/accounts", generalAccountSession.getName().getName())
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(new AccountDTO.Request.Setting(moneyType))
                            .attributes(clientRegistrationId("keycloak-confidential-user"))
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<Account>() {
                            })
                            .block();
                }
            }
        return "redirect:setting";
    }
}
