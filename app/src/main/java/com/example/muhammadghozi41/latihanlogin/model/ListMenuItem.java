package com.example.muhammadghozi41.latihanlogin.model;

/**
 * Created by muhammad.ghozi41 on 14/06/17.
 */

public class ListMenuItem {
    private long id;
    private String iconUrl;
    private String label;
    private String description;

    public ListMenuItem(int id, String icon, String label, String desc) {
        this.id = id;
        this.iconUrl = icon;
        this.label = label;
        this.description = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}