package com.example.ducnguyenvan.uidemo;

import java.sql.Timestamp;

public class Item3Pics extends MyItem {

    private int img1;
    private int img2;
    private int img3;
    private String source;
    private int comments;
    private Timestamp timestamp;

    public Item3Pics(int img1, int img2, int img3, String title, String source, int comments, Timestamp timestamp) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.title = title;
        this.source = source;
        this.comments = comments;
        this.timestamp = timestamp;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public int getImg3() {
        return img3;
    }

    public void setImg3(int img3) {
        this.img3 = img3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
