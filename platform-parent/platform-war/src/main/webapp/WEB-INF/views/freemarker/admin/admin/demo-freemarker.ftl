<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<pre>
freemarker 视图演示
项目是freemarker+jsp的多视图, 优先找freemarker视图,如果找不到,则找寻jsp视图
演示了:
1. freemarker视图
2. 演示JSR349 的后端数据校验
3. 演示前端精确到字段的数据校验失败错误显示
4. 演示i18n(演示方法: 点击页头右上角的切换语言按钮, 然后回到该页)
5. shiro标签在freemarker下的使用
</pre>
=============================================================
<div>
    演示JSR349校验结果(精确到字段):
<#if member??>
    <@spring.bind "member.officeName" />
    <@spring.showErrors ""/>
</#if>
</div>
=============================================================
<div>
    shiro标签使用示例:<br/>
    未登录: shiro:guest: <@shiro.guest>没登陆</@shiro.guest><br/>
    -------------------------------------------------------------<br/>
    登陆后: shiro:user:
<@shiro.user>
    <@shiro.principal property="username"></@shiro.principal>
</@shiro.user><br/>
    -------------------------------------------------------------<br/>
    权限标签演示: <@shiro.hasPermission name="有权限才能访问">没权限</@shiro.hasPermission>
</div>

</body>
</html>