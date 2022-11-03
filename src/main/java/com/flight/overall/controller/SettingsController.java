package com.flight.overall.controller;

import com.flight.overall.dto.SettingsDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Settings;
import com.flight.overall.mapper.EntityMapper;
import com.flight.overall.service.SettingsService;
import com.flight.overall.utils.ErrorHandler;
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
    private SettingsService settingsService;

    @GetMapping("/settings")
    public String openSettings(@AuthenticationPrincipal Account account,
                               Model model) {
        if (account != null) {
            model.addAttribute("settings", settingsService.getSettings(account));
            return "settings/settings";
        }

        return ErrorHandler.handleAccessViolation(model);
    }

    @PostMapping("/settings/save")
    public String saveSettings(@AuthenticationPrincipal Account account,
                               @ModelAttribute SettingsDTO settings) {

        settingsService.saveSettings(account, settings);
        return "redirect:/" + account.getUsername();
    }
}
