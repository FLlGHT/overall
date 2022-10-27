package com.flight.overall.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountDTO {

    @NotNull
    @NotEmpty
    private String fullName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String username;

    public AccountDTO(String fullName, String password, String matchingPassword, String username) {
        this.fullName = fullName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.username = username;
    }

    public AccountDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
