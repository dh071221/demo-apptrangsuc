package com.example.apptrangsuc.model;

public class Review {
    private String content;
    private String rating;
    private String email;
    private String time;

    public Review(String content, String rating, String email, String time) {
        this.content = content;
        this.rating = rating;
        this.email = email;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public String getRating() {
        return rating;
    }

    public String getEmail() {
        return email;
    }

    public String getTime() {
        return time;
    }
}