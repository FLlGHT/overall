package com.flight.overall.entity;

import javax.persistence.*;

@Entity
public class ExternalLink {
    @Id
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    private String title;
    private String link;

    public ExternalLink() {
    }

    public ExternalLink(long id, Profile profile, String title, String link) {
        this.id = id;
        this.profile = profile;
        this.title = title;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
