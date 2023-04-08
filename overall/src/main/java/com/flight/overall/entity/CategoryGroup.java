package com.flight.overall.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class CategoryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_group")
    @SequenceGenerator(name = "category_group", sequenceName = "s_category_group", allocationSize = 1)
    private long id;
    private String title;
    private String description;
    private String color;

    @ColumnDefault("1.00")
    private Double weight;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
