package com.flight.overall.controller;

import com.flight.overall.dto.FilterPaneDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Profile;
import com.flight.overall.service.ContactService;
import com.flight.overall.service.ProfileService;
import com.flight.overall.utils.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ContactsController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ContactService contactService;

    @GetMapping("/{username}/contacts")
    public String getProfileContacts(@PathVariable String username,
                                     @ModelAttribute FilterPaneDTO filterPane,
                                     Account account,
                                     Model model) {
        Optional<Profile> profile = profileService.getProfile(username);

        if (profile.isPresent()) {
            model.addAttribute("contacts", contactService.getProfileContacts(profile.get(), filterPane));
            model.addAttribute("filterPane", contactService.getFilterPane(filterPane));

            return "contacts/contacts";
        }

        return ErrorHandler.handleUserAbsence(model);
    }
}
