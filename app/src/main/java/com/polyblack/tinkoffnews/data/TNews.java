package com.polyblack.tinkoffnews.data;


public class TNews {
    private String title;
    private int id;

    public TNews(){}

    public TNews setter(int id, String title) {
        this.id = id;
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
