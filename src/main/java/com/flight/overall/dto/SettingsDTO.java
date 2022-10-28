package com.flight.overall.dto;

public class SettingsDTO {

    private AccountDTO account;
    private ProfileDTO profile;

    private boolean isClosedProfile = false;
    private boolean isClosedGrades = false;


    public SettingsDTO(AccountDTO account, ProfileDTO profile) {
        this.account = account;
        this.profile = profile;
    }

    public SettingsDTO(AccountDTO account, ProfileDTO profile, boolean isClosedProfile, boolean isClosedGrades) {
        this.account = account;
        this.profile = profile;
        this.isClosedProfile = isClosedProfile;
        this.isClosedGrades = isClosedGrades;
    }

    public SettingsDTO() {
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public boolean isClosedProfile() {
        return isClosedProfile;
    }

    public void setClosedProfile(boolean closedProfile) {
        isClosedProfile = closedProfile;
    }

    public boolean isClosedGrades() {
        return isClosedGrades;
    }

    public void setClosedGrades(boolean closedGrades) {
        isClosedGrades = closedGrades;
    }
}
