<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>区域管理</title>
</head>
<body>

	<%-- 工具栏 --%>
	<div id="toolbar" style="height: auto">
		<shiro:hasPermission name="sys:menu:insert">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add()">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:menu:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:menu:del">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteById()">删除</a>
		</shiro:hasPermission>
	</div>
	<table id="grid"></table>

	<div id="dialog" class="easyui-dialog" title="添加菜单" data-options="iconCls:'icon-save'" style="border: 0; width: 500px;">
		<form id="mainForm" method="post">
			<input name="id" type="hidden">
			<table cellpadding="5">
				<tr>
					<td>上级菜单:</td>
					<td><input name="parentId" id="parentId"></td>
				</tr>
				<tr>
					<td>名称:</td>
					<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>链接(相对于后台路径,绝对路径用"/"开始):</td>
					<td><input class="easyui-textbox" type="text" name="href"></td>
				</tr>
				<tr>
					<td>是否设为功能菜单(展示在左侧菜单):</td>
					<td><select class="easyui-combobox" name="showFlag" data-options="required:true,panelHeight:'auto'">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
				</tr>
				<tr>
					<td>权限标识:</td>
					<td><input class="easyui-textbox" type="text" name="permission"></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input class="easyui-textbox" name="remarks" data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/menu/insert')" id="addSub">添加提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/menu/edit-by-id')" id="editSub">编辑提交</a> <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="closeForm()">取消</a>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/static/scripts/jquery.serializeJSON-2.6.1/jquery.serializejson.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#dialog').dialog('close');
			// 树形结构的表格
			$('#grid').treegrid({
				url : '${ctx}${adminPath}/menu/get-tree',
				fit : true,
				rownumbers : true,
				nowrap : false,
				idField : 'id',
				treeField : 'name',
				toolbar : '#toolbar',
				columns : [ [ {
					title : '名称',
					field : 'name',
					width : '30%'
				}, {
					title : '链接',
					field : 'href',
					width : '30%'
				}, {
					title : '是否显示到左侧菜单',
					field : 'showFlag',
					width : '30%',
					formatter : function(value) {
						if (value == 0)
							return '否';
						else
							return '是';
					}
				}, {
					title : '权限标识',
					field : 'permission',
					width : '30%'
				} ] ],
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
			// 结束树形表

			// 弹出框加载地区的树形
			$('#parentId').combotree({
				url : '${ctx}${adminPath}/menu/get-tree',
				method : 'GET',
				width : '155px'
			});
			// 弹出框树形加载结束

		});

		// 提交form 
		function submitForm(url) {
			if ($('#mainForm').form('validate')) {
				var formJson = $('#mainForm').find('input').not('[value=""]').serializeJSON();
				$.ajax({
					url : url,
					dataType : "json",
					data : JSON.stringify(formJson),
					type : "POST",
					contentType : "application/json;charset=utf-8",
					success : function(data) {
						if (data.success == true) {
							$('#dialog').dialog('close');
							// 重新加载
							$('#grid').treegrid('reload');
							$.messager.show({
								title : data.title,
								msg : data.msg,
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
						} else {
							$.messager.alert("操作提示", data.msg, "error");
						}
					},
					error : function() {
						$.messager.alert("操作提示", "操作失败！", "error");
					}
				});
			} else {

			}
		}
		// 清除form
		function closeForm() {
			$('#mainForm').form('clear');
			$('#dialog').dialog('close');
		}

		// 添加
		function add() {
			$('#dialog').dialog('open');
			$('#mainForm').form('clear');
			$('#addSub').show();
			$('#editSub').hide();
		};

		// 修改
		function edit() {
			var row = $('#grid').treegrid('getSelected');
			//如果选中了
			if (row) {
				$('#dialog').dialog('open');
				$('#addSub').hide();
				$('#editSub').show();
				// 加载到form表单中
				$('#mainForm').form('load', {
					id : row.id,
					parentId : row.parentId,
					name : row.name,
					href : row.href,
					showFlag : row.showFlag,
					permission : row.permission,
					remarks : row.remarks
				});

			}
			//没选中
			else {
				$.messager.alert("操作提示", "请先选中一行！", "error");
			}
		}
		// 修改结束
		function deleteById() {
			var row = $('#grid').treegrid('getSelected');
			if (row) {
				$.ajax({
					url : '${ctx}${adminPath}/menu/del-by-id',
					dataType : "json",
					data : {
						id : row.id
					},
					type : "GET",
					contentType : "application/json;charset=utf-8",
					success : function(data) {
						if (data.success == true) {
							// 重新加载
							$('#grid').treegrid('reload');
							$.messager.show({
								title : data.title,
								msg : data.msg,
								showType : 'fade',
								style : {
									right : '',
									bottom : ''
								}
							});
						} else {
							$.messager.alert("操作提示", data.msg, "error");
						}
					},
					error : function() {
						$.messager.alert("操作提示", "操作失败！", "error");
					}
				});
			} else {
				$.messager.alert("操作提示", "请先选中一行！", "error");
			}
		}
	</script>
</body>
</html>