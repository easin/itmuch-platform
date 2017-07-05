package com.itmuch.core.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.itmuch.core.constants.CodeConstant;
import com.itmuch.core.web.converter.Result;

public class ErrorMsgUtil {
    public static String msgConverter(List<ObjectError> errors) {
        StringBuffer msg = new StringBuffer();
        if (errors != null) {
            for (ObjectError error : errors) {
                msg.append(error.getDefaultMessage() + " ");
            }
        }
        return msg.toString();
    }

    public static Result invalidResult(BindingResult bindingResult) {
        return ErrorMsgUtil.error("参数错误.", ErrorMsgUtil.msgConverter(bindingResult.getAllErrors()), CodeConstant.PARAMTER_ERROR_CODE);
    }

    public static Result error(String title, String msg, int errorCode) {
        Result result = new Result(false, errorCode, title, msg, null);
        return result;
    }
}
