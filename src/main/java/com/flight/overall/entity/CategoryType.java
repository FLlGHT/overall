package com.flight.overall.entity;

/**
 * @author FLIGHT
 * @date 24.11.2022
 */
public enum CategoryType {

    AFFECT("Affect", "Category affects the overall result"),
    NOT_AFFECT("Not affect", "Category does not affect the overall result");

    private final String title;

    private final String description;

    CategoryType(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
