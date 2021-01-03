package com.example.demo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class web_list {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int webid;
    private Long userid;
    private String time;
    @OneToMany(targetEntity = comment.class)
    private List<comment> commentList=new ArrayList<comment>();
    private int goodcount;
    private int commentcount;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWebid(int web_id) {
        this.webid = web_id;
    }

    public void setUserid(Long user_id) {
        this.userid = user_id;
    }

    public int getWebid() {
        return webid;
    }

    public Long getUserid() {
        return userid;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public int getGoodcount() {
        return goodcount;
    }

    public List<comment> getCommentList() {
        return commentList;
    }

    public String getTime() {
        return time;
    }

    public void setCommentcount(int comment_count) {
        this.commentcount = comment_count;
    }

    public void setCommentList(List<comment> commentList) {
        this.commentList = commentList;
    }

    public void setGoodcount(int good_count) {
        this.goodcount = good_count;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

