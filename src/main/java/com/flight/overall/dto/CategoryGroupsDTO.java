package com.flight.overall.dto;

import java.util.List;

public class CategoryGroupsDTO {

    private List<CategoryGroupDTO> categoryGroups;

    public CategoryGroupsDTO() {
    }

    public CategoryGroupsDTO(List<CategoryGroupDTO> categoryGroups) {
        this.categoryGroups = categoryGroups;
    }

    public List<CategoryGroupDTO> getCategoryGroups() {
        return categoryGroups;
    }

    public void setCategoryGroups(List<CategoryGroupDTO> categoryGroups) {
        this.categoryGroups = categoryGroups;
    }
}
