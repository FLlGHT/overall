package com.flight.overall.dto;

public class GradeDTO {

    private long id;
    private int previousGrade;
    private int currentGrade;

    public GradeDTO(long id, int previousGrade, int currentGrade) {
        this.id = id;
        this.previousGrade = previousGrade;
        this.currentGrade = currentGrade;
    }

    public GradeDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPreviousGrade() {
        return previousGrade;
    }

    public void setPreviousGrade(int previousGrade) {
        this.previousGrade = previousGrade;
    }

    public int getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(int currentGrade) {
        this.currentGrade = currentGrade;
    }
}
