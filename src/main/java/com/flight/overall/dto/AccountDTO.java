package com.flight.overall.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountDTO {

    private long id;
    private String firstName;

    private String secondName;

    private String password;
    private String matchingPassword;

    private String username;

    public AccountDTO(String firstName, String password, String matchingPassword, String username) {
        this.firstName = firstName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.username = username;
    }

    public AccountDTO(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public AccountDTO(String firstName, String secondName, String username) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountDTO() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
