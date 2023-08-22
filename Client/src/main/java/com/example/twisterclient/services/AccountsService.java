package com.example.twisterclient.services;

import com.example.twisterclient.models.Account;
import com.example.twisterclient.models.Money;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
public class AccountsService {
    private final WebClient webClient;
    private final GeneralAccountSession generalAccountSession;

    public AccountsService(WebClient webClient,
                           GeneralAccountSession generalAccountSession) {
        this.webClient = webClient;
        this.generalAccountSession = generalAccountSession;
    }

    public Map<Money.Type, List<Account>> load() {
        if (generalAccountSession.getName() != null) {
            var accounts = webClient
                    .get()
                    .uri("/api/general/{name}/accounts", generalAccountSession.getName().getName())
                    .attributes(clientRegistrationId("keycloak-confidential-user"))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<Account>>() {
                    })
                    .block();
            if (accounts != null) return accounts.stream()
                    .collect(Collectors.groupingBy(account -> account.getAmount().getType()));
        }
        return new HashMap<>();
    }
}
