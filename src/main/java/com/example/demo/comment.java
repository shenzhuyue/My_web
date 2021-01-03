package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class comment{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int webid;
    private int userid;
    private String comments;
    private String comment_time;

    public int getUserid() {
        return userid;
    }

    public int getWebid() {
        return webid;
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

    public void setUserid(int user_id) {
        this.userid = user_id;
    }

    public void setWebid(int web_id) {
        this.webid = web_id;
    }
}
