package com.flight.overall.entity;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact")
    @SequenceGenerator(name = "contact", sequenceName = "s_contact", allocationSize = 1)
    private long id;
    @ManyToOne
    @JoinColumn(name = "profile_1")
    private Profile profile1;
    @ManyToOne
    @JoinColumn(name = "profile_2")
    private Profile profile2;

    public Contact() {
    }

    public Contact(Profile profile1, Profile profile2) {
        this.profile1 = profile1;
        this.profile2 = profile2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profile getProfile1() {
        return profile1;
    }

    public void setProfile1(Profile profile1) {
        this.profile1 = profile1;
    }

    public Profile getProfile2() {
        return profile2;
    }

    public void setProfile2(Profile profile2) {
        this.profile2 = profile2;
    }
}
