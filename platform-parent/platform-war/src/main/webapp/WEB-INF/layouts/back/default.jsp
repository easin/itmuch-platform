<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><sitemesh:write property='title' /></title>
<%@include file="/WEB-INF/views/jsp/common/resources/resources_easyui_css.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/resources/resources_jquery.jsp"%>
<%@include file="/WEB-INF/views/jsp/common/resources/resources_easyui_js.jsp"%>
<sitemesh:write property='head' />
</head>
<body class="easyui-layout">
	<sitemesh:write property='body' />
</body>
</html>