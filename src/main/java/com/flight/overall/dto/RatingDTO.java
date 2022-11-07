package com.flight.overall.dto;

public class RatingDTO {
    private long id;
    private CategoryDTO category = new CategoryDTO();

    private GradeDTO grade = new GradeDTO();
    private int rating;


    public RatingDTO(long id, CategoryDTO category, int rating, GradeDTO grade) {
        this.id = id;
        this.category = category;
        this.rating = rating;
        this.grade = grade;
    }

    public RatingDTO() {
    }

    public RatingDTO(CategoryDTO category) {
        this.category = category;
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

    public GradeDTO getGrade() {
        return grade;
    }

    public void setGrade(GradeDTO grade) {
        this.grade = grade;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
