package com.example.twisterclient.web;

import com.example.twisterclient.services.LoadCaseService;
import com.example.twisterclient.services.ModelPlaceholderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class HomeController {
    private final ModelPlaceholderService modelPlaceholderService;
    private final LoadCaseService loadCaseService;

    public HomeController(ModelPlaceholderService modelPlaceholderService,
                          LoadCaseService loadCaseService) {
        this.modelPlaceholderService = modelPlaceholderService;
        this.loadCaseService = loadCaseService;
    }

    @GetMapping
    public String home() {
        return "forward:/home";
    }

    @GetMapping("/home")
    public String home(Model model,
                       @AuthenticationPrincipal DefaultOidcUser user,
                       @Valid @Positive @RequestParam(defaultValue = "1") Integer page) {
        modelPlaceholderService.load(model, user);
        loadCaseService.load(model, page, 5, user);
        return "home";
    }
}
