package com.itmuch.webservice.test.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class JaxRsTestResult {

    private int code;
    private String msg;
    private String title;
    private boolean success;
    private TestUser user;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public TestUser getUser() {
        return this.user;
    }

    public void setUser(TestUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "JaxRsTestResult [code=" + this.code + ", msg=" + this.msg + ", title=" + this.title + ", success=" + this.success + ", user="
                + this.user + "]";
    }

}
