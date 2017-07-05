package com.itmuch.core.util;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号.
     */
    private Integer id;

    /**
     * 用户名.
     */
    private String username;

    /**
     * 邮箱.
     */
    private String email;

    /**
     * 手机号.
     */
    private String mobile;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}