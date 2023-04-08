package com.flight.overall.controller;

import com.flight.overall.service.ContactService;
import com.flight.overall.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ContactsController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ContactService contactService;

//    @GetMapping("/{username}/contacts")
//    public String getProfileContacts(@PathVariable String username,
//                                     @ModelAttribute FilterPaneDTO filterPane,
//                                     Account account,
//                                     Model model) {
//        Optional<Profile> profile = profileService.getProfile(username);
//
//        if (profile.isPresent()) {
//            model.addAttribute("contacts", contactService.getProfileContacts(profile.get(), filterPane));
//            model.addAttribute("filterPane", contactService.getFilterPane(filterPane));
//
//            return "contacts/contacts";
//        }
//
//        return ErrorHandler.handleUserAbsence(model);
//    }
}
