package com.baizhi.entity;

import java.util.List;

/**
 * 上师实体类
 * */
public class Guru {
    private String id;
    private String name;
    private String headPic;//上师图片
    private String sex;//性别
    private String stauts;//状态
    private List<Article> children;

    public Guru() {
    }

    public Guru(String id, String name, String headPic, String sex, String stauts, List<Article> children) {
        this.id = id;
        this.name = name;
        this.headPic = headPic;
        this.sex = sex;
        this.stauts = stauts;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

    public List<Article> getChildren() {
        return children;
    }

    public void setChildren(List<Article> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Guru{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", headPic='" + headPic + '\'' +
                ", sex='" + sex + '\'' +
                ", stauts='" + stauts + '\'' +
                ", children=" + children +
                '}';
    }
}