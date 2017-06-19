package com.example.muhammadghozi41.latihanlogin.model;

/**
 * Created by muhammad.ghozi41 on 14/06/17.
 */

public class ListMenuItem {
    private int id;
    private String iconUrl;
    private String label;
    private String description;
    private int parent_id;
    public ListMenuItem(){

    }
    public ListMenuItem(int id, String icon, String label, String desc) {
        this.id = id;
        this.iconUrl = icon;
        this.label = label;
        this.description = desc;
    }
    public ListMenuItem(int id, String icon, String label, String desc,int parent_id) {
        this.id = id;
        this.iconUrl = icon;
        this.label = label;
        this.description = desc;
        this.parent_id=parent_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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



    public String getDescription() {
        return description;
    }



    public String getMenuLabel() {
        return label;
    }

    public String getMenuDesc() {
        return description;
    }
    public void setMenuLabel(String menuLabel) {
        this.label = menuLabel;

    }


    public int getParentId() {
        return parent_id;
    }


    public void setMenuDesc(String menuDesc) {
        this.description = menuDesc;
    }

    public void setParentId(int parentId) {
        this.parent_id = parentId;
    }
}