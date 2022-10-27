package com.flight.overall.entity;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String fullName;
    private LocalDate dateOfBirth;
    private Integer overallRating;
    private String description;

    public Profile(long id, String username, String fullName, LocalDate dateOfBirth,
                   Integer overallRating, String description) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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
}
