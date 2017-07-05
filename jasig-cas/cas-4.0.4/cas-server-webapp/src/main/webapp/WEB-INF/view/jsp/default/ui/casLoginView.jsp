<%@ page session="true"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>平台 | 登陆</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="static/AdminLTE-2.3.0/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="static/font-awesome-4.3.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="static/ionicons-2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="static/AdminLTE-2.3.0/dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="static/AdminLTE-2.3.0/plugins/iCheck/square/blue.css">
<link rel="shortcut icon" href="favicon.ico" />
<style type="text/css">
.body-bg {
	background-image: url('https://localhost:8443/cas/static/1.jpg');
	background-size:cover; 
}
</style>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition login-page body-bg">
	<div class="login-box">
		<div class="login-logo">
			<a href="../../index2.html"><b>平台</b>登陆</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">单点登录认证系统</p>
			<form:form method="post" id="fm1" class="login-form" commandName="${commandName}" htmlEscape="true">
				<form:errors path="*" id="msg" cssClass="alert alert-danger" element="div" />
				<div class="form-group has-feedback">
					<form:input cssClass="form-control placeholder-no-fix" placeholder="请输入用户名" id="username" path="username" autocomplete="false" htmlEscape="true" />
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<form:password cssClass="form-control placeholder-no-fix" placeholder="请输入密码" id="password" path="password" autocomplete="off" htmlEscape="true" />
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" id="rememberMe" name="rememberMe" value="true" /> 记住我一周
							</label> <input type="hidden" name="lt" value="${loginTicket}" /> <input type="hidden" name="execution" value="${flowExecutionKey}" /> <input type="hidden" name="_eventId"
								value="submit" /> </label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">

						<button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
					</div>
					<!-- /.col -->
				</div>
			</form:form>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 2.1.4 -->
	<script src="static/AdminLTE-2.3.0/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="static/AdminLTE-2.3.0/bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="static/AdminLTE-2.3.0/plugins/iCheck/icheck.min.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>
</body>
</html>
