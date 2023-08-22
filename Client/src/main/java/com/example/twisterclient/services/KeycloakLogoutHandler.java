package com.example.twisterclient.services;

import com.example.twisterclient.models.GeneralAccount;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Component
public class KeycloakLogoutHandler implements LogoutSuccessHandler {
    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(KeycloakLogoutHandler.class);

    public KeycloakLogoutHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    private void logoutFromKeycloak(OidcUser user) {
        String endSessionEndpoint = user.getIssuer() + "/protocol/openid-connect/logout";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(endSessionEndpoint)
                .queryParam("id_token_hint", user.getIdToken().getTokenValue());

        webClient.get()
                .uri(builder.toUriString())
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        logger.info("Successfully logged out from Keycloak");
                    } else {
                        logger.error("Could not propagate logout to Keycloak");
                    }
                    return clientResponse.bodyToMono(String.class);
                })
                .block();
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logoutFromKeycloak((OidcUser) authentication.getPrincipal());
        response.sendRedirect(request.getContextPath());
    }
}
