package com.dvelop.archetype.plugins.http.dto;

public class FeatureDto {
    String title;
    String subtitle;
    String description;
    String summary;
    String url;
    String color;

    public FeatureDto(String title, String subtitle, String description, String summary, String url, String color, String icon) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.summary = summary;
        this.url = url;
        this.color = color;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    String icon;
}
