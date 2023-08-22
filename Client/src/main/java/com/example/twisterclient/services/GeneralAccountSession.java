package com.example.twisterclient.services;

import com.example.twisterclient.models.GeneralAccount;
import com.example.twisterclient.models.GeneralAccountName;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Component
@SessionScope
public class GeneralAccountSession {
    private GeneralAccountName name;
    private final WebClient webClient;

    public GeneralAccountSession(WebClient webClient) {
        this.webClient = webClient;
    }

    public GeneralAccount getAccount() {
        if (getName() == null) return new GeneralAccount();
        return webClient
                .get()
                .uri("/api/general/{name}", getName().getName())
                .attributes(clientRegistrationId("keycloak-confidential-user"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<GeneralAccount>() {
                })
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getStatusCode() == HttpStatus.UNAUTHORIZED ? Mono.empty() : Mono.error(ex))
                .block();
    }

    public GeneralAccountName getName() {
        if (name == null) {
            name = webClient
                    .get()
                    .uri("/api/general")
                    .attributes(clientRegistrationId("keycloak-confidential-user"))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<GeneralAccountName>() {
                    })
                    .onErrorResume(WebClientResponseException.class,
                            ex -> ex.getStatusCode() == HttpStatus.CONFLICT || ex.getStatusCode() == HttpStatus.UNAUTHORIZED ?
                                    Mono.empty() : Mono.error(ex))
                    .block();
        }
        return name;
    }
}
