<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="font-size: 30px">LOGO</div>
<div style="position: absolute; top: 30px; right: 10px;">
	<shiro:guest><a href="${ctx }${adminPath}/login">登陆</a></shiro:guest>
	<shiro:user>
	<a href="${ctx }${adminPath}/change-language">切换语言${session }</a>
	欢迎:<shiro:principal property="username"></shiro:principal>
		<a href="${ctx }${adminPath}/logout">注销</a>
	</shiro:user>
</div>
<%-- 页头比较土,根据自己系统自己写吧.主要的标签已经写好. --%>