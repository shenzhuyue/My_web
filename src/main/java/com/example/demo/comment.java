package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class comment{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int web_id;
    private int user_id;
    private String comments;
    private String comment_time;

    public int getUser_id() {
        return user_id;
    }

    public int getWeb_id() {
        return web_id;
    }

    public String getComment_time() {
        return comment_time;
    }

    public String getComments() {
        return comments;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setWeb_id(int web_id) {
        this.web_id = web_id;
    }
}
