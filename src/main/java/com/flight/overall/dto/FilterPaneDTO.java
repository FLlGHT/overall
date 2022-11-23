package com.flight.overall.dto;

import com.flight.overall.entity.SortType;

import java.util.List;

public class FilterPaneDTO {

    private List<CategoryDTO> categories;
    private CategoryDTO selectedCategory;
    private CategoryDTO emptyCategory;
    private SortType sortType;

    public FilterPaneDTO(List<CategoryDTO> categories) {
        this.categories = categories;
        emptyCategory = new CategoryDTO();
    }

    public FilterPaneDTO() {
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

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public CategoryDTO getEmptyCategory() {
        return emptyCategory;
    }

    public void setEmptyCategory(CategoryDTO emptyCategory) {
        this.emptyCategory = emptyCategory;
    }
}
