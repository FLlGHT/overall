package com.flight.overall.dto;

public class ImageDTO {

    private long id;

    private String name;

    private byte[] content;

    public ImageDTO() {
    }

    public ImageDTO(long id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.content = image;
    }

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
