package com.itmuch.webservice.test.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class TestUser {
    @Override
    public String toString() {
        return "TestUser [id=" + this.id + ", username=" + this.username + ", age=" + this.age + ", sex=" + this.sex + "]";
    }

    private Integer id;
    private String username;
    private Integer age;
    private Byte sex;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getSex() {
        return this.sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }
}
