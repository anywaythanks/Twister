package com.example.twisterclient.web;

import com.example.twisterclient.models.dto.SellItemDtoRequest;
import com.example.twisterclient.services.InventoryService;
import com.example.twisterclient.services.ModelPlaceholderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/me/inventory/{nameInventory}")
public class InventoryController {
    private final ModelPlaceholderService modelPlaceholderService;
    private final InventoryService inventoryService;

    public InventoryController(ModelPlaceholderService modelPlaceholderService,
                               InventoryService inventoryService) {
        this.modelPlaceholderService = modelPlaceholderService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String inventory(Model model,
                            @AuthenticationPrincipal DefaultOidcUser user,
                            @Valid @PathVariable @NotBlank String nameInventory) {
        modelPlaceholderService.load(model, user);
        inventoryService.load(model, nameInventory);
        model.addAttribute("sell", new SellItemDtoRequest());
        return "inventory/inventory";
    }

    @PostMapping("sell/{nameItem}/{accountNumber}")
    public String sell(@Valid @PathVariable @NotBlank String nameInventory,
                       @Valid @PathVariable @NotBlank String accountNumber,
                       @Valid @PathVariable @NotBlank String nameItem,
                       SellItemDtoRequest sellItemDtoRequest) {
        inventoryService.sell(nameInventory, accountNumber, nameItem, sellItemDtoRequest);
        return "redirect:" + ServletUriComponentsBuilder
                .fromPath("/me/inventory/{nameInventory}")
                .buildAndExpand(nameInventory).toUriString();
    }
}
