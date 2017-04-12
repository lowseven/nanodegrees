package com.example.lowseven.guardiannewsapp;

public class Article {
    private String webTitle;
    private String webUrl;
    private String sectionName;
    private String thumbnail;

    public Article() {
        this.webTitle = "";
        this.webUrl = "";
        this.sectionName = "";
        this.thumbnail = "";
    }

    public Article( String webTitle, String webUrl, String sectionName, String thumbnail) {
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.sectionName = sectionName;
        this.thumbnail = thumbnail;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String gethumbnail() {
        return thumbnail;
    }
}
