package com.itmuch.platform.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itmuch.platform.admin.domain.User;
import com.itmuch.platform.controller.common.BaseController;

/**
 * 登陆controller, 用于未整合cas的情况
 * @author zhouli
 */
@Controller
@RequestMapping("${adminPath}")
public class LoginAdminController extends BaseController {

    @RequestMapping("/login")
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return "admin/index/login";
        } else {
            // 登陆成功/以及已经登陆过的用户,直接跳到后台首页
            return "redirect:" + this.adminPath;
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String preLogin(User user, RedirectAttributes ra) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            ra.addFlashAttribute("loginError", "账号或密码有误.");
            ra.addFlashAttribute("username", user.getUsername());
            ra.addFlashAttribute("password", user.getPassword());
            return "redirect:" + this.adminPath + "/login";
        }

        return "redirect:" + this.adminPath;
    }

}
