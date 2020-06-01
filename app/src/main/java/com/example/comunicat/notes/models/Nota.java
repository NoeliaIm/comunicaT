package com.example.comunicat.notes.models;

import java.util.Date;

public class Nota {
    private String title;
    private String body;
    private Date date;

    public Nota(String title, String body, Date date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }
}
