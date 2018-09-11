package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureView {
    private Long id;

    @JsonProperty("url")
    private String picture_url;

    public PictureView(Long id, String picture_url) {
        this.id = id;
        this.picture_url = picture_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
