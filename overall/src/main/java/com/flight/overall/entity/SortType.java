package com.flight.overall.entity;

public enum SortType {

    DESC("Descending order"), ASC("Ascending order");

    private final String text;

    SortType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
