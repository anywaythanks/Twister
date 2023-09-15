package com.example.twisterclient.services;

import com.example.twisterclient.models.Case;
import com.example.twisterclient.models.Page;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
public class LoadCaseService {
    private final WebClient webClient;
    private final GeneralAccountSession generalAccountSession;
    private final TypesItemService typesItemService;
    public LoadCaseService(WebClient webClient,
                           GeneralAccountSession generalAccountSession,
                           TypesItemService typesItemService) {
        this.webClient = webClient;
        this.generalAccountSession = generalAccountSession;
        this.typesItemService = typesItemService;
    }

    public void load(Model model, int page, int size, DefaultOidcUser user) {
        Page<Case> result;
        if (user == null || generalAccountSession.getName() == null) {
            result = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/public/case")
                            .queryParam("page", page - 1)
                            .queryParam("size", size)
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Page<Case>>() {
                    })
                    .block();
        } else {
            result = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/general/{name}/actual_case")
                            .queryParam("page", page - 1)
                            .queryParam("size", size)
                            .build(generalAccountSession.getName().getName()))
                    .attributes(clientRegistrationId("keycloak-confidential-user"))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Page<Case>>() {
                    })
                    .block();
        }
        if (result != null && result.getPage() != null) result.setPage(result.getPage() + 1);
        model.addAttribute("page", result);
        typesItemService.load(model);
    }

    public void load(Model model, String caseName, DefaultOidcUser user) {
        Case resultCase;
        if (user == null || generalAccountSession.getName() == null) {
            resultCase = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/public/case/{caseName}")
                            .build(caseName))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Case>() {
                    })
                    .block();
        } else {
            resultCase = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/general/{name}/actual_case/{caseName}")
                            .build(generalAccountSession.getName().getName(), caseName))
                    .attributes(clientRegistrationId("keycloak-confidential-user"))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Case>() {
                    })
                    .block();
        }
        model.addAttribute("selectedCase", resultCase);
        typesItemService.load(model);
    }
}
