package com.flight.overall.dto;

import java.util.List;

public class CategoriesDTO {

    private List<CategoryDTO> categories;

    private List<CategoryGroupDTO> groups;

    public CategoriesDTO(List<CategoryDTO> categories, List<CategoryGroupDTO> groups) {
        this.categories = categories;
        this.groups = groups;
    }

    public CategoriesDTO(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public CategoriesDTO() {
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryGroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<CategoryGroupDTO> groups) {
        this.groups = groups;
    }
}
