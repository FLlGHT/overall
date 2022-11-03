package com.flight.overall.controller;

import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Profile;
import com.flight.overall.service.ContactService;
import com.flight.overall.service.ProfileService;
import com.flight.overall.utils.ErrorHandler;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    private ContactService contactService;


    @GetMapping("/{username}")
    public String getProfile(@PathVariable String username,
                             @AuthenticationPrincipal Account account,
                             Model model) {
        Optional<Profile> profile = profileService.getProfile(username);

        if (profile.isPresent()) {
            model.addAttribute("profile", profileService.getProfileInfo(profile.get(), account));
            return "profile/profile";
        }

        return ErrorHandler.handleUserAbsence(model);
    }

    @PostMapping("/profile-save")
    public String saveProfile(@ModelAttribute ProfileDTO profileDTO,
                              @AuthenticationPrincipal Account account,
                              Model model) {
        Profile profile = profileService.saveProfile(profileDTO, account);
        return "redirect:/" + profile.getUsername();
    }

    @PostMapping("/add-contact")
    public String addToContacts(@ModelAttribute ProfileDTO profileDTO,
                                @AuthenticationPrincipal Account account,
                                Model model) {
        contactService.addContact(account, profileDTO);
        return "redirect:/" + profileDTO.getUsername();
    }

    @GetMapping("/{username}/contacts")
    public String getProfileContacts(@PathVariable String username,
                                     Account account,
                                     Model model) {
        Optional<Profile> profile = profileService.getProfile(username);

        if (profile.isPresent()) {
            model.addAttribute("profile", profileService.getProfileContacts(profile.get(), account));
            return "profile/contacts";
        }

        return ErrorHandler.handleUserAbsence(model);
    }

}
