package com.flight.overall.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class OverallRating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "overall_rating")
    @SequenceGenerator(name = "overall_rating", sequenceName = "s_overall_rating", allocationSize = 1)
    private long id;
    @ColumnDefault("0")
    private int rating;

    public OverallRating() {
    }

    public OverallRating(long id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
