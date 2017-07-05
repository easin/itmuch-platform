package com.itmuch.platform.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmuch.platform.controller.common.BaseController;

@Controller
@RequestMapping("${adminPath}")
public class IndexAdminController extends BaseController {
    @RequestMapping("")
    public String index() {
        return "admin/index/index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "admin/index/welcome";
    }

}
