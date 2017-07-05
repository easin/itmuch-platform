<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>区域管理</title>
</head>
<body>
	<div class="easyui-panel" title="New Topic" style="width: 400px;border: solid 1px red;">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td>Name:</td>
						<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input class="easyui-textbox" type="text" name="email" data-options="required:true,validType:'email'"></input></td>
					</tr>
					<tr>
						<td>Subject:</td>
						<td><input class="easyui-textbox" type="text" name="subject" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>Message:</td>
						<td><input class="easyui-textbox" name="message" data-options="multiline:true" style="height: 60px"></input></td>
					</tr>
					<tr>
						<td>Language:</td>
						<td><select class="easyui-combobox" name="language"><option value="ar">Arabic</option>
								<option value="tr">Turkish</option>
								<option value="uk">Ukrainian</option>
								<option value="vi">Vietnamese</option>
						</select></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>


</body>
</html>