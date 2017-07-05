/**
 * Copyright 2014 cnshangquan All Rights Reserved.
 * 项目名：cnshangquan-core
 * 包名：com.cnshangquan.core.exception
 * 文件名：ErrorCodeConstant.java 
 * 日期：2014-04-28 08:58
 */
package com.itmuch.core.constants;

/**
 * 异常类对应编号.
 * 
 * @author zhouli
 * 
 */
public class CodeConstant {

    /**
     * 成功.
     */
    public static final int SUCCESS_CODE = 1000;

    /**
     * 不清楚的错误编号.
     */
    public static final int UNKNOW_ERROR_CODE = 1001;
    /**
     * 没有查到数据
     */
    public static final int DATA_NOT_FOUND = 1002;
    /**
     * 参数出错编号
     */
    public static final int PARAMTER_ERROR_CODE = 1003;
    /**
     * Mybatis 发生异常
     */
    public static final int MYBATIS_SYSTEM_ERROR = 1004;

    public static final int SHIRO_ERROR = 1005;

    public static final int DUPLICATE_DATA = 1006;

}
