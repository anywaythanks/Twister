package com.example.twisterclient.web;

import com.example.twisterclient.exception.NoExistAccountException;
import com.example.twisterclient.models.Twist;
import com.example.twisterclient.services.GeneralAccountSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@RestController
@RequestMapping("/me/{number}/twist")
public class TwistController {
    private final WebClient webClient;
    private final GeneralAccountSession generalAccountSession;

    public TwistController(WebClient webClient, GeneralAccountSession generalAccountSession) {
        this.webClient = webClient;
        this.generalAccountSession = generalAccountSession;
    }

    @PostMapping(path = "{name}")
    public Twist twist(@Valid @PathVariable @NotBlank String number,
                       @Valid @PathVariable @NotBlank String name) {
        if (generalAccountSession.getName() == null) throw new NoExistAccountException();
        var twist = this.webClient
                .post()
                .uri("/api/general/{name}/accounts/{number}/twist/{caseName}",
                        generalAccountSession.getName().getName(), number, name)
                .attributes(clientRegistrationId("keycloak-confidential-user"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Twist>() {
                })
                .block();
        return twist;
    }
}
