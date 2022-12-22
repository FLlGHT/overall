package com.flight.overall.service;

import com.flight.overall.dto.*;
import com.flight.overall.entity.*;
import com.flight.overall.mapper.EntityMapper;
import com.flight.overall.repository.ContactRepository;
import com.flight.overall.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private EntityMapper entityMapper;

    public void addContact(Account account, ProfileDTO profile) {
        Profile sender = account.getProfile();
        Profile recipient = profileRepository.findProfile(profile.getId());

        contactRepository.save(new Contact(sender, recipient));
        contactRepository.save(new Contact(recipient, sender));
    }

    public ContactsInfo getProfileContacts(Profile profile, Filter filterPane) {
        ProfileDTO profileDTO = entityMapper.toProfileDTO(profile);
        List<ContactDTO> contactsDTO = getContacts(profile, filterPane);

        return new ContactsInfo(profileDTO, contactsDTO, getFilterPane(filterPane));
    }

    private List<ContactDTO> getContacts(Profile profile, Filter filterPane) {
        if (filterPane == null || filterPane.getSelectedCategory() == null)
            return entityMapper.toDefaultProfileContacts(profile);

        long selectedCategory = filterPane.getSelectedCategory().getId();
        List<Profile> contacts = contactRepository.getSortedContacts(profile.getId(), selectedCategory);
        List<ContactDTO> contactsDTO = new ArrayList<>();
        contacts.forEach(contact -> {
            int rating = ratingService.getProfileRating(contact.getId(), selectedCategory);
            contactsDTO.add(entityMapper.toContactDTO(contact, rating));
        });

        return contactsDTO;
    }

    public Filter getFilterPane(Filter filter) {
        List<CategoryDTO> categories = categoryService.getCategoriesList();

        if (filter == null)
            return new Filter(categories);

        filter.setCategories(categories);
        return filter;
    }
}
