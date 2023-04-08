package com.flight.overall.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image")
    @SequenceGenerator(name = "image", sequenceName = "s_image", allocationSize = 1)
    private long id;
    private String name;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
