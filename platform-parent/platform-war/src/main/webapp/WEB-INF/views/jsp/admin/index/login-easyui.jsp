<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登陆 - 后台管理</title>
</head>
<body>
	<div id="dialog">
		<form id="mainForm" method="post" action="${ctx }/${adminPath}/login">
		${loginError }
			<input name="id" type="hidden">
			<table cellpadding="5">
				<tr>
					<td>用户名:</td>
					<td><input class="easyui-textbox" type="text" name="loginName" value="${loginName }" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input class="easyui-textbox" type="password" name="password" value="${password }" data-options="required:true"></input></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#dialog').show().dialog({
				modal : false,
				title:'登录',
				closable : false,
				iconCls : 'icon-ok',
				buttons : [ {
					id : 'loginBtn',
					text : '登录',
					handler : function() {
						if($('#mainForm').form('validate')){
							$('#mainForm').submit();
						}
					}
				} ],
			});
		});
	</script>
</body>
</html>