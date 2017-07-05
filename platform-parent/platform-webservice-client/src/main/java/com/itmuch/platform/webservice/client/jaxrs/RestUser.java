package com.itmuch.platform.webservice.client.jaxrs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class RestUser {
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
