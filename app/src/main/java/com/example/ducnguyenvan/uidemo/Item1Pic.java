package com.example.ducnguyenvan.uidemo;

import java.sql.Timestamp;

public class Item1Pic extends MyItem {
    private int img;
    private String source;
    private int comments;
    private Timestamp timestamp;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item1Pic(int img, String title, String source, int comments, Timestamp timestamp) {
        this.img = img;
        this.title = title;
        this.source = source;
        this.comments = comments;
        this.timestamp = timestamp;
    }
}

