package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class user {

    //    设置一个主键id    主键自增策略
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String telephone;
    @OneToMany(targetEntity = user.class)
    private List<user> attention=new ArrayList<user>();

    public List<user> getAttention() {
        return attention;
    }

    public void setAttention(List<user> attention) {
        this.attention = attention;
    }

    protected user() {

    }

    public user(Long id,String username,String password,String telephone) {
        this.id=id;
        this.password=password;
        this.username=username;
        this.telephone=telephone;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}