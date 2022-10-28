package com.flight.overall.dto;

public class SettingsDTO {

    private AccountDTO account;
    private ProfileDTO profile;
    private boolean closedProfile;
    private boolean closedGrades;


    public SettingsDTO(AccountDTO account, ProfileDTO profile) {
        this.account = account;
        this.profile = profile;
    }

    public SettingsDTO(AccountDTO account, ProfileDTO profile, boolean closedProfile, boolean closedGrades) {
        this.account = account;
        this.profile = profile;
        this.closedProfile = closedProfile;
        this.closedGrades = closedGrades;
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
        return closedProfile;
    }

    public void setClosedProfile(boolean closedProfile) {
        this.closedProfile = closedProfile;
    }

    public boolean isClosedGrades() {
        return closedGrades;
    }

    public void setClosedGrades(boolean closedGrades) {
        this.closedGrades = closedGrades;
    }
}
