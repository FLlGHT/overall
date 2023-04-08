package com.flight.overall.dto;

import java.util.List;

public class ContactsInfo {

    private ProfileDTO profile;

    private List<ContactDTO> contacts;

    private Filter filter;

    public ContactsInfo() {
    }


    public ContactsInfo(ProfileDTO profile, List<ContactDTO> contacts, Filter filter) {
        this.profile = profile;
        this.contacts = contacts;
        this.filter = filter;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
