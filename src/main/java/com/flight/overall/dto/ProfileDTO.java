package com.flight.overall.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTO {

    private long id;
    private String firstName;
    private String secondName;
    private String username;
    private String dateOfBirth;
    private String placeOfResidence;
    private Integer overallRating;
    private String description;
    private String email;
    private List<RatingGroupDTO> ratingGroups = new ArrayList<>();
    private MultipartFile image;
    private String imageString;
    private List<ProfileDTO> contacts;
    private boolean canAddToContacts;

    public ProfileDTO() {
    }

    public ProfileDTO(long id, String firstName, String secondName, String username, Integer overallRating, String imageString) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.overallRating = overallRating;
        this.imageString = imageString;
    }

    public ProfileDTO(long id, String firstName, String secondName, String username, String dateOfBirth, Integer overallRating,
                      String placeOfResidence, String description, String imageString, List<RatingGroupDTO> ratingGroups,
                      List<ProfileDTO> contacts, boolean canAddToContacts) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.overallRating = overallRating;
        this.placeOfResidence = placeOfResidence;
        this.description = description;
        this.imageString = imageString;
        this.ratingGroups = ratingGroups;
        this.contacts = contacts;
        this.canAddToContacts = canAddToContacts;
    }

    public ProfileDTO(long id, String firstName, String secondName, String username, String dateOfBirth,
                      String description, String email, String placeOfResidence) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public List<RatingGroupDTO> getRatingGroups() {
        return ratingGroups;
    }

    public void setRatingGroups(List<RatingGroupDTO> ratingGroups) {
        this.ratingGroups = ratingGroups;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public List<ProfileDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ProfileDTO> contacts) {
        this.contacts = contacts;
    }

    public boolean getCanAddToContacts() {
        return canAddToContacts;
    }

    public void setCanAddToContacts(boolean canAddToContacts) {
        this.canAddToContacts = canAddToContacts;
    }
}
