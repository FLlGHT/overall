package com.flight.overall.controller;

import com.flight.overall.entity.Profile;
import com.flight.overall.entity.Rating;
import com.flight.overall.service.ProfileService;
import com.flight.overall.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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
    public String getProfiles(Model model,
                              @PathVariable String username) {
        Optional<Profile> optional = profileService.getProfile(username);

        if (optional.isPresent()) {
            Profile profile = optional.get();
            List<Rating> ratings = ratingService.getProfileRatings(profile.getId());

            model.addAttribute("profile", profile);
            model.addAttribute("ratings", ratings);
            return "profile";
        }
        return "error";
    }

    @GetMapping("/settings")
    public String openSettings(Model model) {
        return "settings";
    }
}
