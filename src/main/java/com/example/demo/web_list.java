package com.example.demo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class web_list {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int webid;
    private int userid;
    private String username;
    private String time;
    private int goodcount;
    private int commentcount;
    private String content;
    private String imagepath;

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWebid(int web_id) {
        this.webid = web_id;
    }

    public void setUserid(int user_id) {
        this.userid = user_id;
    }

    public int getWebid() {
        return webid;
    }

    public int getUserid() {
        return userid;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public int getGoodcount() {
        return goodcount;
    }



    public String getTime() {
        return time;
    }

    public void setCommentcount(int comment_count) {
        this.commentcount = comment_count;
    }



    public void setGoodcount(int good_count) {
        this.goodcount = good_count;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

