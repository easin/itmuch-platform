package com.itmuch.platform.controller.admin;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.itmuch.platform.controller.common.BaseController;

@Controller
@RequestMapping("${adminPath}")
public class I18nAdminController extends BaseController {
    /**
     * 切换语言 参考文档: http://www.cnblogs.com/liukemng/p/3750117.html
     * @param session 会话
     * @see http://www.cnblogs.com/liukemng/p/3750117.html
     * @return 切换首页
     */
    @RequestMapping("/change-language")
    public String changeLang(HttpSession session) {

        Locale localeCN = new Locale("zh", "CN");
        Locale localeEN = new Locale("en");

        Locale locale = LocaleContextHolder.getLocale();
        if ("zh_CN".equals(locale.toString())) {
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, localeEN);
        } else {
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, localeCN);
        }
        return String.format("redirect:%s", this.adminPath);
    }
}
