package com.flight.overall.controller;

import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.dto.SettingsDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.mapper.EntityMapper;
import com.flight.overall.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingsController {

    @Autowired
    private EntityMapper mapper;

    @Autowired
    private SettingsService settingsService;

    @GetMapping("/settings")
    public String openSettings(@AuthenticationPrincipal Account account,
                               Model model) {

        return settingsService.getSettings(model, account);
    }

    @PostMapping("/settings/save")
    public String saveSettings(@AuthenticationPrincipal Account account,
                               @ModelAttribute SettingsDTO settings) {

        settingsService.saveSettings(account, settings);
        return "redirect:/" + account.getUsername();
    }
}
