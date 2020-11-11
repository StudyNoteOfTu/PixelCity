package com.bingyan.pixcelcity.bean;

public class PostMsg {
    int img_url;
    String title;
    String detail;
    String date;

    boolean seen;

    public PostMsg(int img_url, String title, String detail, String date) {
        this.img_url = img_url;
        this.title = title;
        this.detail = detail;
        this.date = date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
