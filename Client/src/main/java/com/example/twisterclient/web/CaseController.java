package com.example.twisterclient.web;

import com.example.twisterclient.services.GeneralAccountSession;
import com.example.twisterclient.services.LoadCaseService;
import com.example.twisterclient.services.ModelPlaceholderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("/cases")
public class CaseController {
    private final ModelPlaceholderService modelPlaceholderService;
    private final LoadCaseService loadCaseService;

    public CaseController(ModelPlaceholderService modelPlaceholderService,
                          LoadCaseService loadCaseService) {
        this.modelPlaceholderService = modelPlaceholderService;
        this.loadCaseService = loadCaseService;
    }

    @GetMapping
    public String cases(Model model,
                        @AuthenticationPrincipal DefaultOidcUser user,
                        @Valid @Positive @RequestParam(defaultValue = "1") Integer page) {
        modelPlaceholderService.load(model, user);
        loadCaseService.load(model, page, 10, user);
        return "cases/cases";
    }

    @GetMapping("/{caseName}")
    public String getCase(Model model,
                          @Valid @PathVariable @NotBlank String caseName,
                          @AuthenticationPrincipal DefaultOidcUser user) {
        modelPlaceholderService.load(model, user);
        loadCaseService.load(model, caseName, user);
        return "cases/case";
    }
}
