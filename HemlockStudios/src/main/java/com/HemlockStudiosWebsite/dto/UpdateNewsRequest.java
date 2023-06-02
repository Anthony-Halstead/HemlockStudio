package com.HemlockStudiosWebsite.dto;

public class UpdateNewsRequest {
    private Integer id;
    private String anouncement;
    private String title;
    private String description;
    private String body;
    private String[] imgUrls;


    public String getAnouncement() {
        return anouncement;
    }
    public void setAnouncement(String anouncement) {
        this.anouncement = anouncement;
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
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String[] getImgUrls() {
        return imgUrls;
    }
    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

}
