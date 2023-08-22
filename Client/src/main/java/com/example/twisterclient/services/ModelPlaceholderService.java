package com.example.twisterclient.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ModelPlaceholderService {
    private final GeneralAccountSession generalAccountSession;
    private final AccountsService accountsService;
    private final String fileServerPath;

    public ModelPlaceholderService(GeneralAccountSession generalAccountSession,
                                   AccountsService accountsService,
                                   @Value("${file-server-path}") String fileServerPath) {
        this.generalAccountSession = generalAccountSession;
        this.accountsService = accountsService;
        this.fileServerPath = fileServerPath;
    }

    public void load(Model model, DefaultOidcUser user) {
        model.addAttribute("fileServerPath", fileServerPath);
        model.addAttribute("user", user);
        var ga = generalAccountSession.getAccount();
        model.addAttribute("general", ga);
        if (ga.getName() != null) {
            model.addAttribute("accounts", accountsService.load());
        }
    }
}
