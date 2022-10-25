package com.flight.overall.dto;

public class RatingDTO {
    private long id;
    private CategoryDTO category;
    private int rating;
    private long grade;

    public RatingDTO(long id, CategoryDTO category, int rating) {
        this.id = id;
        this.category = category;
        this.rating = rating;
    }

    public RatingDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
