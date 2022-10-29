package com.flight.overall.dto;

import java.util.List;

public class ProfileDTO {

    private long id;
    private String fullName;
    private String username;
    private String dateOfBirth;
    private String placeOfResidence;
    private Integer overallRating;
    private String description;

    private String email;
    private List<RatingDTO> ratings;

    public ProfileDTO() {
    }

    public ProfileDTO(long id, String fullName, String username, String dateOfBirth, Integer overallRating,
                      String placeOfResidence, String description, List<RatingDTO> ratings) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.overallRating = overallRating;
        this.placeOfResidence = placeOfResidence;
        this.description = description;
        this.ratings = ratings;
    }

    public ProfileDTO(long id, String fullName, String username, String dateOfBirth,
                      String description, String email, String placeOfResidence) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.email = email;
        this.placeOfResidence = placeOfResidence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDTO> ratings) {
        this.ratings = ratings;
    }

    public Integer getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Integer overallRating) {
        this.overallRating = overallRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
