package com.flight.overall.entity;

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
}
