package com.flight.overall.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile")
    @SequenceGenerator(name = "profile", sequenceName = "s_profile", allocationSize = 1)
    private long id;
    private String username;
    private String firstName;
    private String secondName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private Integer overallRating;
    @Column(length = 255)
    private String description;
    private String placeOfResidence;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
    private String role;
    @OneToOne
    @JoinColumn(name = "profile_image_id")
    private Image profileImage;

    @OneToMany(mappedBy = "profile")
    @JsonIgnoreProperties(value = "profile", allowGetters = true)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "profile")
    @JsonIgnoreProperties(value = "profile", allowGetters = true)
    private List<GroupRating> groupRatings;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "profile", allowSetters = true)
    private List<ExternalLink> links;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "contact",
            joinColumns = @JoinColumn(name = "profile_1"),
            inverseJoinColumns = @JoinColumn(name = "profile_2"))
    private List<Profile> contacts;

    public Profile(long id, String username, String firstName, String secondName, Date dateOfBirth,
                   Integer overallRating, String description) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.overallRating = overallRating;
        this.description = description;
    }

    public Profile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fullName) {
        this.firstName = fullName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public List<Profile> getContacts() {
        return contacts;
    }

    public void setContacts(List<Profile> contacts) {
        this.contacts = contacts;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<GroupRating> getGroupRatings() {
        return groupRatings;
    }

    public void setGroupRatings(List<GroupRating> groupRatings) {
        this.groupRatings = groupRatings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<ExternalLink> getLinks() {
        return links;
    }

    public void setLinks(List<ExternalLink> links) {
        this.links = links;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Profile profile = (Profile) o;
        return id == profile.id && Objects.equals(username, profile.username) && Objects.equals(
                firstName, profile.firstName) && Objects.equals(
                secondName, profile.secondName) && Objects.equals(
                dateOfBirth, profile.dateOfBirth) && Objects.equals(
                overallRating, profile.overallRating) && Objects.equals(
                description, profile.description) && Objects.equals(
                placeOfResidence, profile.placeOfResidence) && Objects.equals(
                email, profile.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, secondName, dateOfBirth, overallRating, description,
                            placeOfResidence,
                            email, profileImage, contacts
        );
    }
}
