package com.itmuch.platform.controller.admin;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmuch.core.log.Pagination;
import com.itmuch.core.page.PageVo;
import com.itmuch.platform.admin.service.LogService;
import com.itmuch.platform.admin.vo.LogQueryVo;

@Controller
@RequestMapping("${adminPath}/log")
public class LogAdminController {
    @Resource
    private LogService logService;

    @RequestMapping("")
    @RequiresPermissions("sys:log:view")
    public String index() {
        return "admin/log/log";
    }

    @RequestMapping("/list-paged")
    @ResponseBody
    @RequiresPermissions("sys:log:view")
    public Pagination list(LogQueryVo queryVo, PageVo vo) {
        return this.logService.selectPaged(queryVo, vo);

    }
}
