package com.flight.overall.dto;

public class ExternalLinkDTO {

    private long id;
    private String title;
    private String link;

    public ExternalLinkDTO(long id, String title, String link) {
        this.id = id;
        this.title = title;
        this.link = link;
    }

    public ExternalLinkDTO() {
    }

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
