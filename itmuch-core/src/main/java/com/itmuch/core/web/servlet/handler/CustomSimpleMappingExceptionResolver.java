package com.itmuch.core.web.servlet.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itmuch.core.constants.CodeConstant;
import com.itmuch.core.web.converter.Result;

public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.warn("控制器异常.", ex);

        // Expose ModelAndView for chosen error view.
        String viewName = this.determineViewName(ex, request);

        if (viewName != null) {
            // JSP格式返回
            if (!((request.getHeader("accept").indexOf("application/json") > -1) || ((request.getHeader("X-Requested-With") != null) && (request
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)))) {
                // 如果不是异步请求
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = this.determineStatusCode(request, viewName);
                if (statusCode != null) {
                    this.applyStatusCodeIfPossible(request, response, statusCode);
                }
                // request.setAttribute("Exception", ex.getMessage());
                Exception exc = null;
                if (ex instanceof MyBatisSystemException) {
                    exc = new Exception("Mybatis发生异常.");
                } else if (ex instanceof UnauthorizedException) {
                    exc = new Exception("授权异常, 无此权限.");
                } else if (ex instanceof AuthorizationException) {
                    exc = new Exception("授权异常, 无此权限.");
                } else if (ex instanceof java.sql.SQLException) {
                    exc = new Exception("SQL有误.");
                } else if (ex instanceof Exception) {
                    exc = new Exception(ex.getMessage());
                } else {
                    exc = new Exception("未知异常, 请联系管理员.");
                }
                return this.getModelAndView(viewName, exc, request);
            }
            // JSON格式返回
            else {

                Result result = null;

                if (ex instanceof MyBatisSystemException) {
                    result = new Result(false, CodeConstant.MYBATIS_SYSTEM_ERROR, "异常发生", "Mybatis发生异常.", null);

                } else if (ex instanceof UnauthorizedException) {
                    result = new Result(false, CodeConstant.SHIRO_ERROR, "异常发生", "授权异常, 无此权限.", null);
                } else if (ex instanceof AuthorizationException) {
                    result = new Result(false, CodeConstant.SHIRO_ERROR, "异常发生", "授权异常, 无此权限.", null);
                } else {
                    result = new Result(false, CodeConstant.UNKNOW_ERROR_CODE, "异常发生", "未知异常, 请联系管理员", null);
                }
                // result.setMsg(ex.getMessage());
                byte[] byteArray = null;
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    byteArray = objectMapper.writeValueAsBytes(result);
                    FileCopyUtils.copy(byteArray, response.getOutputStream());
                } catch (IOException e) {
                    LOGGER.error("处理错误编码出错", e);
                }
                // 本想直接return null，但是发现return null时，会将抛出的异常打印到控制台，干脆return一个新的视图了。
                return new ModelAndView();
            }
        } else {
            return null;
        }
    }
}