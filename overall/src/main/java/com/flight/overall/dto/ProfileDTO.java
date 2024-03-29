package com.flight.overall.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String username;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String placeOfResidence;
    private Integer overallRating;
    private String description;
    private String email;
    private List<RatingGroupDTO> ratingGroups = new ArrayList<>();
    private ImageDTO image;
    private MultipartFile uploadedFile;
    private List<ContactDTO> contacts;
    private Integer numberOfContacts;
    private boolean canAddToContacts;
    private List<ExternalLinkDTO> externalLinks = new ArrayList<>();
    private CompanyDTO company;
    private String role;

    public ProfileDTO() {
    }

    public ProfileDTO(long id, String firstName, String lastName, String username, Integer overallRating, ImageDTO image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.overallRating = overallRating;
        this.image = image;
    }

    public ProfileDTO(long id, String firstName, String lastName, String username, Date dateOfBirth, Integer overallRating,
                      String placeOfResidence, String description, ImageDTO image, List<RatingGroupDTO> ratingGroups,
                      List<ContactDTO> contacts, Integer numberOfContacts, boolean canAddToContacts, List<ExternalLinkDTO> externalLinks, CompanyDTO company,
                      String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.overallRating = overallRating;
        this.placeOfResidence = placeOfResidence;
        this.description = description;
        this.image = image;
        this.ratingGroups = ratingGroups;
        this.contacts = contacts;
        this.numberOfContacts = numberOfContacts;
        this.canAddToContacts = canAddToContacts;
        this.externalLinks = externalLinks;
        this.company = company;
        this.role = role;
    }

    public ProfileDTO(long id, String firstName, String lastName, String username, Date dateOfBirth,
                      String description, String email, String placeOfResidence, List<ExternalLinkDTO> externalLinks,
                      CompanyDTO company, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.email = email;
        this.placeOfResidence = placeOfResidence;
        this.externalLinks = externalLinks;
        this.company = company;
        this.role = role;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isCanAddToContacts() {
        return canAddToContacts;
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

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }

    public MultipartFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(MultipartFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    public boolean getCanAddToContacts() {
        return canAddToContacts;
    }

    public void setCanAddToContacts(boolean canAddToContacts) {
        this.canAddToContacts = canAddToContacts;
    }

    public List<ExternalLinkDTO> getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(List<ExternalLinkDTO> externalLinks) {
        this.externalLinks = externalLinks;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getNumberOfContacts() {
        return numberOfContacts;
    }

    public void setNumberOfContacts(Integer numberOfContacts) {
        this.numberOfContacts = numberOfContacts;
    }
}
