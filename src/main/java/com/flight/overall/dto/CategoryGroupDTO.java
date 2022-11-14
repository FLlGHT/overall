package com.flight.overall.dto;

public class CategoryGroupDTO {

    private long id;
    private String title;
    private String description;

    public CategoryGroupDTO(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
}
