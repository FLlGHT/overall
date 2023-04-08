package com.flight.overall.entity;

/**
 * @author FLIGHT
 * @date 24.11.2022
 */
public enum CategoryType {

    IN_DIRECT_RATIO("In direct ratio", "The higher the category value, the better", "bi bi-arrow-up-short"),
    IN_INVERSE_RATIO("In inverse ratio", "The lower the category value, the better", "bi bi-arrow-down-short"),
    NOT_AFFECT("Not affect", "Category does not affect the overall result", "bi bi-arrow-left-right");

    private final String title;

    private final String description;

    private final String icon;

    CategoryType(String title, String description, String icon) {
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
