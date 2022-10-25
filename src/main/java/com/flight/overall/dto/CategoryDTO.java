package com.flight.overall.dto;

public class CategoryDTO {

    private long id;
    private String title;

    public CategoryDTO(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public CategoryDTO() {
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
}
