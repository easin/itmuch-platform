<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日志管理</title>
</head>
<body>

	<%-- 工具栏 --%>
	<div id="toolbar" style="height: auto">
		<form id="mainForm">
			<table>
				<tr>
					<td style="padding-left: 15px;">创建时间: <input class="easyui-datetimebox" name="startTime"></input>-<input class="easyui-datetimebox" name="endTime"> </input>
					</td>
					<td style="padding-left: 15px;">级别: <select class="easyui-combobox" name="level" data-options="panelHeight:'auto'"> 
							<option value="">请选择级别</option>
							<option value="DEBUG">DEBUG</option>
							<option value="WARN">WARN</option>
							<option value="ERROR">ERROR</option>
							<option value="INFO">INFO</option>
					</select></td>
					<td style="padding-left: 15px;">IP: <input class="easyui-textbox" name="ip"></td>
					<!-- <td style="padding-left: 15px;">详情: <input class="easyui-textbox" name="message"></td> -->
					<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="search2()">搜索</a></td>
				</tr>
			</table>
		</form>

	</div>
	<table id="grid"></table>

	<script type="text/javascript" src="${ctx }/static/scripts/jquery.serializeJSON-2.6.1/jquery.serializejson.js"></script>
	<script type="text/javascript" src="${ctx }/static/scripts/uploadify/js/jquery.uploadify.js"></script>
	<link href="${ctx }/static/scripts/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		$(function() {
			$('#dialog').dialog('close');
			var grid = $('#grid').datagrid({
				title : '',
				url : '${ctx}${adminPath}/log/list-paged',
				fit : true,
				rownumbers : true,
				nowrap : false,
				striped : true,
				pagination : true,
				singleSelect : true,
				idField : 'id',
				sortName : 'createDate',
				sortOrder : 'desc',
				pageSize : 10,
				pageList : [ 10, 20, 30, 50 ],
				columns : [ [ {
					title : '时间',
					field : 'timestamp',
					width : '10%'
				}, {
					title : '线程',
					field : 'thread',
					width : '10%'
				}, {
					title : '级别',
					field : 'level',
					width : '5%'
				}, {
					title : 'IP',
					field : 'ip',
					width : '7%'
				}, {
					title : 'Logger',
					field : 'logger',
					width : '30%'
				}, {
					title : '详情',
					field : 'message',
					width : '35%'
				}, {
					title : '异常',
					field : 'exception',
					width : '35%'
				} ] ],
				toolbar : '#toolbar',
				onBeforeLoad : function(param) {
					$.messager.progress({
						text : '数据加载中....'
					});
				},
				onLoadSuccess : function(data) {
					$.messager.progress('close');
				},
				onLoadError : function() {
					$.messager.progress('close');
				}
			});
			// 结束datagrid

		});

		function search2() {
			var formJson = $('#mainForm').find('input').not('[value=""]').serializeJSON();
			// alert(JSON.stringify(formJson));
			$('#grid').datagrid({queryParams:formJson});
		}
	</script>
</body>
</html>