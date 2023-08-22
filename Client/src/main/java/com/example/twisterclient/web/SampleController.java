package com.example.twisterclient.web;

import com.example.twisterclient.models.Money;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Controller
@RequestMapping("/")
public class SampleController {

    private final WebClient webClient;

    public SampleController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/types")
    public String getProducts(Model model) {
        var types = this.webClient
                .get()
                .uri("/api/public/money/type")
                .attributes(clientRegistrationId("keycloak-confidential-user"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Money.Type>>() {
                })
                .block();
        model.addAttribute("types", types);
        return "types";
    }
}
