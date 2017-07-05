<%-- 配置常用的标签库 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %><%--用于读取配置文件 --%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set value="${pageContext.request.contextPath}" var="ctx" />
<c:set value="${fns:getAdminPath()}" var="adminPath" />
<c:set value="${fns:getFrontPath()}" var="frontPath" />