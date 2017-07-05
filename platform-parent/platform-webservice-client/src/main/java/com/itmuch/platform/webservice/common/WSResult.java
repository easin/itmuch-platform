package com.itmuch.platform.webservice.common;

/**
 * 基础的webservice 返回包装类, 适用于仅作提示, 而无数据返回的情况, 其他情况可以继承它.
 * 如果有数据返回, 请继承该类.
 * @author zhouli
 */
public class WSResult {
    protected boolean success = true;
    protected int code = 200;
    protected String msg = "操作成功";
    protected String title = "成功";

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

}
