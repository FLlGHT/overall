package com.flight.overall.entity;

import javax.persistence.*;

/**
 * @author FLIGHT
 * @date 29.10.2022
 */

@Entity
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "settings")
    @SequenceGenerator(name = "settings", sequenceName = "s_settings", allocationSize = 1)
    private long id;

    private boolean isProfileClosed;

    private boolean isGradesClosed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isProfileClosed() {
        return isProfileClosed;
    }

    public void setProfileClosed(boolean profileClosed) {
        isProfileClosed = profileClosed;
    }

    public boolean isGradesClosed() {
        return isGradesClosed;
    }

    public void setGradesClosed(boolean gradesClosed) {
        isGradesClosed = gradesClosed;
    }
}
