package com.itmuch.core.springmvc.duplicateform;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 防止重复提交过滤器
 * 参考文档: http://my.oschina.net/mushui/blog/143397 https://github.com/junjun-dachi/spring-mvc-util
 * @author zhouli
 */
public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(AvoidDuplicateSubmissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
            if (annotation != null) {
                boolean genToken = annotation.genToken();
                if (genToken) {
                    HttpSession session = request.getSession();
                    System.out.println(session);
                    session.setAttribute("token", UUID.randomUUID().toString());
                }

                boolean validToken = annotation.validToken();
                if (validToken) {
                    if (this.isRepeatSubmit(request)) {
                        LOG.warn("发生重复提交, url = {}" + request.getServletPath());
                        return false;
                    }
                    // 由于同一个页面可能存在多个表单, 故而remove不能用
                    // request.getSession(false).removeAttribute("token");
                    request.getSession().setAttribute("token", UUID.randomUUID().toString());
                }
            }
        }
        return true;
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }

}