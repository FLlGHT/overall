package com.flight.overall.controller;

import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.service.ContactService;
import com.flight.overall.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private ContactService contactService;


    @PostMapping("/add-contact")
    public String addToContacts(@ModelAttribute ProfileDTO profileDTO,
                                @AuthenticationPrincipal Account account,
                                Model model) {
        contactService.addContact(account, profileDTO);
        return "redirect:/" + profileDTO.getUsername();
    }
}
