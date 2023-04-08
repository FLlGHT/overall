package com.flight.overall.controller;


import com.flight.overall.dto.ContactsInfo;
import com.flight.overall.dto.Filter;
import com.flight.overall.entity.Profile;
import com.flight.overall.service.ContactService;
import com.flight.overall.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContactsRestController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ContactService contactService;


    @PostMapping("/{username}/contacts")
    public ContactsInfo getProfileContacts(@PathVariable String username,
                                           @RequestBody ContactsInfo contactsInfo) {
        Optional<Profile> profile = profileService.getProfile(username);
        Filter filterPane = contactsInfo.getFilter();

        if (profile.isPresent()) {
            return contactService.getProfileContacts(profile.get(), filterPane);
        }

        return new ContactsInfo();
    }
}
