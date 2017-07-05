package com.itmuch.platform.controller.demo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmuch.platform.admin.vo.UserAdminRegVo;

/**
 * freemarker 视图演示
 * 项目是freemarker+jsp的多视图, 优先找freemarker视图,如果找不到,则找寻jsp视图
 * 演示了:
 * 1. freemarker视图
 * 2. 演示JSR349 的后端数据校验
 * 3. 演示前端精确到字段的数据校验失败错误显示
 * 4. 演示i18n
 * 5. shiro标签在freemarker下的使用
 * @author zhouli
 */
@Controller
@RequestMapping("/demo/freemarker")
public class DemoFreemarkerViewController {

    @RequestMapping("")
    public String index(@Valid @ModelAttribute("member") UserAdminRegVo user, BindingResult res, HttpServletRequest req) {

        // 先到messages_zh_CN.properties中找member.mobile.empty, 如果找不到,就用"用户名已存在."
        res.rejectValue("officeName", "member.username.exists", "用户名已存在.");
        req.setAttribute("member", new UserAdminRegVo());
        return "admin/admin/demo-freemarker";
    }
}
