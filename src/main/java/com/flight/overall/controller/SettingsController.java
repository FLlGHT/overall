package com.flight.overall.controller;

import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.dto.SettingsDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.mapper.EntityMapper;
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

    @GetMapping("/settings")
    public String openSettings(@AuthenticationPrincipal Account account,
                               Model model) {

        model.addAttribute("settings", mapper.toSettingsDTO(account));
        return "settings";
    }

    @PostMapping("/settings/save")
    public String saveSettings(@AuthenticationPrincipal Account account,
                               @ModelAttribute SettingsDTO settings) {
        boolean isClosed = settings.isClosedProfile();

        return "redirect:/" + account.getUsername();
    }
}
