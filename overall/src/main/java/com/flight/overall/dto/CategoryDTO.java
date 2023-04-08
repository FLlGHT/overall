package com.flight.overall.dto;

import com.flight.overall.entity.CategoryType;

public class CategoryDTO {

    private long id;
    private String title;
    private CategoryGroupDTO categoryGroup = new CategoryGroupDTO();
    private CategoryType categoryType;
    private String description;
    private Double weight;

    public CategoryDTO(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public CategoryDTO(long id, String title, CategoryGroupDTO categoryGroup, CategoryType categoryType,
                       String description, Double weight) {
        this.id = id;
        this.title = title;
        this.categoryGroup = categoryGroup;
        this.categoryType = categoryType;
        this.description = description;
        this.weight = weight;
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

    public CategoryGroupDTO getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(CategoryGroupDTO categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
