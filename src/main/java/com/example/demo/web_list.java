package com.example.demo;


import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Entity
public class web_list {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int web_id;
    private int user_id;
    private String time;
    @OneToMany(targetEntity = comment.class)
    private List<comment> commentList=new ArrayList<comment>();
    private int good_count;
    private int comment_count;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWeb_id(int web_id) {
        this.web_id = web_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWeb_id() {
        return web_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getGood_count() {
        return good_count;
    }

    public List<comment> getCommentList() {
        return commentList;
    }

    public String getTime() {
        return time;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setCommentList(List<comment> commentList) {
        this.commentList = commentList;
    }

    public void setGood_count(int good_count) {
        this.good_count = good_count;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

