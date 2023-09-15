package com.example.twisterclient.services;

import com.example.twisterclient.models.Inventory;
import com.example.twisterclient.models.InventoryName;
import com.example.twisterclient.models.InventorySlot;
import com.example.twisterclient.models.dto.SellItemDtoRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
public class InventoryService {
    private final WebClient webClient;
    private final GeneralAccountSession generalAccountSession;
    private final TypesItemService typesItemService;

    public InventoryService(WebClient webClient,
                            GeneralAccountSession generalAccountSession,
                            TypesItemService typesItemService) {
        this.webClient = webClient;
        this.generalAccountSession = generalAccountSession;
        this.typesItemService = typesItemService;
    }

    public void loadNames(Model model) {
        if (generalAccountSession.getName() == null) throw new IllegalArgumentException();//TODO
        var names = webClient
                .get()
                .uri("/api/general/{name}/inventory",
                        generalAccountSession.getName().getName())
                .attributes(clientRegistrationId("keycloak-confidential-user"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<InventoryName>>() {
                })
                .block();
        model.addAttribute("names", names);
    }

    public void load(Model model, String nameInventory) {
        if (generalAccountSession.getName() == null) throw new IllegalArgumentException();//TODO
        var inventory = webClient
                .get()
                .uri("/api/general/{name}/inventory/{nameInventory}",
                        generalAccountSession.getName().getName(), nameInventory)
                .attributes(clientRegistrationId("keycloak-confidential-user"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Inventory>() {
                })
                .block();
        model.addAttribute("inventory", inventory);
        typesItemService.load(model);
    }

    public void sell(String nameInventory, String accountNumber, String nameItem, SellItemDtoRequest sellItemDtoRequest) {
        if (generalAccountSession.getName() == null) throw new IllegalArgumentException();//TODO
        webClient.post()
                .uri("/api/general/{name}/inventory/{nameInventory}/{nameItem}/sell/{accountNumber}",
                        generalAccountSession.getName().getName(), nameInventory, nameItem, accountNumber)
                .bodyValue(sellItemDtoRequest)
                .attributes(clientRegistrationId("keycloak-confidential-user"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<InventorySlot>() {
                })
                .block();
    }
}
