package com.flight.overall.dto;

public class RatingsUpdate {

    private RatingDTO rating;

    private RatingGroupDTO ratingGroup;

    private ProfileDTO profile;

    public RatingsUpdate() {
    }

    public RatingsUpdate(RatingDTO rating, RatingGroupDTO ratingGroup, ProfileDTO profile) {
        this.rating = rating;
        this.ratingGroup = ratingGroup;
        this.profile = profile;
    }

    public RatingDTO getRating() {
        return rating;
    }

    public void setRating(RatingDTO rating) {
        this.rating = rating;
    }

    public RatingGroupDTO getRatingGroup() {
        return ratingGroup;
    }

    public void setRatingGroup(RatingGroupDTO ratingGroup) {
        this.ratingGroup = ratingGroup;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }
}
