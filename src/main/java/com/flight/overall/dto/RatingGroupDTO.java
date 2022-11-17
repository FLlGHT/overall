package com.flight.overall.dto;

import java.util.ArrayList;
import java.util.List;

public class RatingGroupDTO {

    private long id;

    private String title;

    private String description;

    private int groupRating;

    private List<RatingDTO> ratings = new ArrayList<>();

    public RatingGroupDTO() {
    }

    public RatingGroupDTO(long id, String title, String description) {
        this.id = id;
        this.title = title;
    }

    public RatingGroupDTO(long id, String title, String description, List<RatingDTO> ratings) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ratings = ratings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGroupRating() {
        return groupRating;
    }

    public void setGroupRating(int groupRating) {
        this.groupRating = groupRating;
    }

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDTO> ratings) {
        this.ratings = ratings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
