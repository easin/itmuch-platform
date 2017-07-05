package com.itmuch.core.web.converter;

import com.itmuch.core.constants.CodeConstant;

/**
 * 返回结果包装类.
 * @author Zhouli
 *
 */
public class Result {
    private boolean success = true;
    private int code = CodeConstant.SUCCESS_CODE;
    private String msg = "操作成功";
    private String title = "成功";
    private Object data;

    @Deprecated
    public Result() {
    };

    public Result(String title, String msg) {

        this.title = title;
        this.msg = msg;
        this.success = true;
        this.code = CodeConstant.SUCCESS_CODE;
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result(boolean success, int code, String title, String msg, Object data) {
        this.success = success;
        this.code = code;
        this.title = title;
        this.msg = msg;
        this.data = data;
    }

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

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
