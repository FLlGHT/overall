package com.flight.overall.dto;

import java.util.List;

public class Filter {

    private List<CategoryDTO> categories;
    private CategoryDTO selectedCategory;

    public Filter(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public Filter() {
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public CategoryDTO getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryDTO selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
}
