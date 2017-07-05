<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>哈哈</title>
</head>
<body>
	<form action="${ctx }/demo/email/send">
		想要发送到的地址:<input type="email" name="to"> 
		<#if sendRes??>
			<#if sendRes>
			发送成功
			<#else>
			发送失败
			</#if>
		</#if><br>
		提交:<input type="submit">
	</form>
</body>
</html>