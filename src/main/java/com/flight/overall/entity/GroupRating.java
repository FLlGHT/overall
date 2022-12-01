package com.flight.overall.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class GroupRating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_rating")
    @SequenceGenerator(name = "group_rating", sequenceName = "s_group_rating", allocationSize = 1)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CategoryGroup categoryGroup;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    private int rating;

    public GroupRating() {
    }

    public GroupRating(CategoryGroup categoryGroup, Profile profile) {
        this.categoryGroup = categoryGroup;
        this.profile = profile;
    }

    public GroupRating(long id, CategoryGroup categoryGroup, Profile profile, int rating) {
        this.id = id;
        this.categoryGroup = categoryGroup;
        this.profile = profile;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(CategoryGroup categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
