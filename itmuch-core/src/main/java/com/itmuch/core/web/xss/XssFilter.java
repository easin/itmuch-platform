package com.itmuch.core.web.xss;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 简单的XSS防御, 后期可能参考:http://blog.csdn.net/shadowsick/article/details/41747931 实现使用Shiro的防御办法.
 * @author qianbao
 *
 */
public class XssFilter implements Filter {
    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        @SuppressWarnings("rawtypes")
        Enumeration enumeration = req.getAttributeNames();
        // System.out.println(names);
        Map<String, Object> map = null;
        if (enumeration.hasMoreElements()) {
            map = new HashMap<String, Object>();
            while (enumeration.hasMoreElements()) {
                String inputName = (String) enumeration.nextElement();//获取元素名  
                map.put(inputName, request.getParameter(inputName));//以表单名作为key  
            }
        }
        // System.out.println(map);
        chain.doFilter(new XssHttpServletRequestWrapper(req), response);
    }

}