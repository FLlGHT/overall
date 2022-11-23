package com.flight.overall.dto;

import java.util.List;

public class ContactsDTO {

    private ProfileDTO profile;

    private List<ProfileDTO> contacts;

    public ContactsDTO() {
    }

    public ContactsDTO(ProfileDTO profile, List<ProfileDTO> contacts) {
        this.profile = profile;
        this.contacts = contacts;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public List<ProfileDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ProfileDTO> contacts) {
        this.contacts = contacts;
    }
}
