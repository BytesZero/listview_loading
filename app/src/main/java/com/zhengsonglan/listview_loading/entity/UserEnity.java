package com.zhengsonglan.listview_loading.entity;

/**
 * Created by Administrator on 2015/4/20.
 */
public class UserEnity {

    private String name,icon;

    public UserEnity(String icon, String name) {
        this.icon = icon;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
