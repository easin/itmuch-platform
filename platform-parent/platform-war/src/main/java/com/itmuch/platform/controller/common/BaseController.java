package com.itmuch.platform.controller.common;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController {
    /**
     * 管理基础路径
     */
    @Value("${adminPath}")
    protected String adminPath;
}
