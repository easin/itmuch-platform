<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登陆 - 后台管理</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/admin/index.css">
</head>
<body>
	<form id="login" method="post" action="${ctx }${adminPath}/login">
		${loginError }

		<h1>后台登陆</h1>
		<fieldset id="inputs">
			<input name="username" type="text" placeholder="用户名" value="${username }" autofocus required>
			<input name="password" id="password" type="password" placeholder="密码" required>
		</fieldset>
		<input type="checkbox" name="rememberMe" value="true" /> 记住我
		<fieldset id="actions">
			<input type="submit" id="submit" value="登陆">
		</fieldset>
	</form>
</body>
</html>