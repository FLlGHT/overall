package com.flight.overall.controller;

import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.service.ProfileService;
import com.flight.overall.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{username}")
    public String getProfile(@PathVariable String username,
                             @AuthenticationPrincipal Account account,
                             Model model) {
        return profileService.getProfile(username, account, model);
    }

    @PostMapping("/profile-save")
    public String saveProfile(@ModelAttribute ProfileDTO profile,
                              @AuthenticationPrincipal Account account,
                              Model model) {
        return profileService.saveProfile(profile, account, model);
    }

    @GetMapping("/settings")
    public String openSettings(Model model) {
        return "settings";
    }
}
