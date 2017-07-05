<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>区域管理</title>
</head>
<body>

	<%-- 工具栏 --%>
	<div id="toolbar" style="height: auto">
		<shiro:hasPermission name="cms:category:insert">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add()">添加</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="cms:category:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="cms:category:del">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteById()">删除</a>
		</shiro:hasPermission>
	</div>
	<table id="grid"></table>



	<div id="dialog" class="easyui-dialog" title="添加栏目" data-options="iconCls:'icon-save'" style="border: 0; width: 500px;">
		<form id="mainForm" method="post">
			<input name="id" type="hidden">
			<table cellpadding="5">
				<tr>
					<td>上级栏目:</td>
					<td><input name="parentId" id="parentId"></td>
				</tr>
				<tr>
					<td>栏目名称:</td>
					<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>关键词:</td>
					<td><input class="easyui-textbox" type="text" name="keywords" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>描述:</td>
					<td><input class="easyui-textbox" type="text" name="description" data-options="required:true"></td>
				</tr>
				<tr>
					<td>展示于导航:</td>
					<td><select name="inMenu">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
				</tr>
				<tr>
					<td>跳转方式:</td>
					<td><select name="target">
							<option value="_BLANK">新窗口打开</option>
							<option value="_SELF">本窗口打开</option>
					</select></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input class="easyui-textbox" name="remarks" data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/category/insert')" id="addSub">添加提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/category/edit-by-id')" id="editSub">编辑提交</a> <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="closeForm()">取消</a>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/static/scripts/jquery.serializeJSON-2.6.1/jquery.serializejson.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#dialog').dialog('close');
			// 树形结构的表格
			$('#grid').treegrid({
				url : '${ctx}${adminPath}/category/get-tree',
				fit : true,
				rownumbers : true,
				nowrap : false,
				idField : 'id',
				treeField : 'name',
				toolbar : '#toolbar',
				columns : [ [ {
					title : '栏目名称',
					field : 'name',
					width : '30%'
				}, {
					title : '关键词',
					field : 'keywords',
					width : '10%'
				}, {
					title : '展示于导航',
					field : 'inMenu',
					formatter : function(value, rec) {
						if (value = 1) {
							return '是';
						} else {
							return '否';
						}
					},
					width : '10%'
				}, {
					title : '跳转方式',
					field : 'target',
					width : '10%'
				}, {
					title : '备注',
					field : 'remarks',
					width : '10%'
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

			// 弹出框加载树形
			$('#parentId').combotree({
				url : '${ctx}${adminPath}/category/get-tree',
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
				// alert(row.id);
				// 加载到form表单中
				$('#mainForm').form('load', {
					id : row.id,
					parentId : row.parentId,
					name : row.name,
					code : row.code,
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
					url : '${ctx}${adminPath}/category/del-by-id',
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