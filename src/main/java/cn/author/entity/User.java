package cn.author.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    private Integer id;

    private String name;
    @JSONField(format="yyyy-MM-dd")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}