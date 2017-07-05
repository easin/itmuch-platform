package com.itmuch.platform.controller.demo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itmuch.platform.util.email.EmailUtil;

/**
 * 防止Captcha机器人登陆
 * @author liuwang
 *
 */
@Controller
@RequestMapping("/demo/email")
public class DemoEmailController {
    @Resource
    private EmailUtil emailUtil;

    /**
     * 发送邮箱demo 页面
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "demo/email";
    }

    /**
     * freemarker 模板发送邮件 DEMO
     * @param to 收信人
     * @return 演示页面
     */
    @RequestMapping("/send")
    public ModelAndView send(String to) {
        boolean sendRes = this.emailUtil.sendMailByTemplate(to, "主题", null, "demo/email-template/email-template.ftl");
        return new ModelAndView("demo/email").addObject("sendRes", sendRes);
    }
}