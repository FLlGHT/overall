package com.flight.overall.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category")
    @SequenceGenerator(name = "category", sequenceName = "s_category", allocationSize = 1)
    private long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "category_group_id")
    private CategoryGroup categoryGroup;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(length = 512)
    private String description;

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

    public CategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(CategoryGroup categoryGroup) {
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
