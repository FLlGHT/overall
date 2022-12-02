package com.flight.overall.dto;

public class CategoryGroupDTO {

    private long id;
    private String title;
    private String description;
    private String color;

    public CategoryGroupDTO(long id, String title, String description, String color) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public CategoryGroupDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
