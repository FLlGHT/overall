package com.flight.overall.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating")
    @SequenceGenerator(name = "rating", sequenceName = "s_rating", allocationSize = 1)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_rating_id")
    private GroupRating groupRating;
    private long total;
    private long count;
    private int rating;

    public Rating(Category category, Profile profile, long total, long count, int rating) {
        this.category = category;
        this.profile = profile;
        this.total = total;
        this.count = count;
        this.rating = rating;
    }

    public Rating(GroupRating groupRating, Category category, Profile profile) {
        this.groupRating = groupRating;
        this.category = category;
        this.profile = profile;
        this.total = 0;
        this.count = 0;
        this.rating = 0;
    }

    public Rating() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public GroupRating getGroupRating() {
        return groupRating;
    }

    public void setGroupRating(GroupRating groupRating) {
        this.groupRating = groupRating;
    }
}
