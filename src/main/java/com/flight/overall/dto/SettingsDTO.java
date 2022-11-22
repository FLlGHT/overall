package com.flight.overall.dto;

public class SettingsDTO {

    private long id;
    private AccountDTO account = new AccountDTO();
    private ProfileDTO profile = new ProfileDTO();
    private boolean closedProfile;
    private boolean closedGrades;


    public SettingsDTO(AccountDTO account, ProfileDTO profile) {
        this.account = account;
        this.profile = profile;
    }

    public SettingsDTO(long id, AccountDTO account, ProfileDTO profile, boolean closedProfile, boolean closedGrades) {
        this.id = id;
        this.account = account;
        this.profile = profile;
        this.closedProfile = closedProfile;
        this.closedGrades = closedGrades;
    }

    public SettingsDTO(AccountDTO account, ProfileDTO profile, boolean closedProfile, boolean closedGrades) {
        this.account = account;
        this.profile = profile;
        this.closedProfile = closedProfile;
        this.closedGrades = closedGrades;
    }

    public SettingsDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
