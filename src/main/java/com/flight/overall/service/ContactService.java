package com.flight.overall.service;

import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Contact;
import com.flight.overall.entity.Profile;
import com.flight.overall.repository.ContactRepository;
import com.flight.overall.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public void addContact(Account account, ProfileDTO profile) {
        Profile sender = account.getProfile();
        Profile recipient = profileRepository.findProfile(profile.getId());

        contactRepository.save(new Contact(sender, recipient));
        contactRepository.save(new Contact(recipient, sender));
    }
}
