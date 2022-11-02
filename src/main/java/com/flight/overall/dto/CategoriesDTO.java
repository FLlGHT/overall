package com.flight.overall.dto;

import java.util.List;

public class CategoriesDTO {

    private List<CategoryDTO> categories;

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
}
