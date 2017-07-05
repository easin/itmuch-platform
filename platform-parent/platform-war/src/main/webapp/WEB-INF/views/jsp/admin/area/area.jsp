<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>区域管理</title>
</head>
<body>

	<%-- 工具栏 --%>
	<div id="toolbar" style="height: auto">
		<shiro:hasPermission name="sys:area:insert">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add()">添加</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="sys:area:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
		</shiro:hasPermission>

		<shiro:hasPermission name="sys:area:del">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteById()">删除</a>
		</shiro:hasPermission>
	</div>
	<table id="grid"></table>



	<div id="dialog" class="easyui-dialog" title="添加区域" data-options="iconCls:'icon-save'" style="border: 0; width: 500px;">
		<form id="mainForm" method="post">
			<input name="id" type="hidden">
			<table cellpadding="5">
				<tr>
					<td>上级区域:</td>
					<td><input name="parentId" id="parentId"></td>
				</tr>
				<tr>
					<td>区域名称:</td>
					<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>区域编码:</td>
					<td><input class="easyui-textbox" type="text" name="code" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>区域类型:</td>
					<td><select id="type" name="type" class="easyui-combobox" data-options="required:true,panelHeight:'auto'" style="width: 155px">
							<option value="1">国家</option>
							<option value="2">省份、直辖市</option>
							<option value="3">地市</option>
							<option value="4">区县</option>
					</select></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input class="easyui-textbox" name="remark" data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/area/insert')" id="addSub">添加提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/area/edit-by-id')" id="editSub">编辑提交</a> <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="closeForm()">取消</a>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/static/scripts/jquery.serializeJSON-2.6.1/jquery.serializejson.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#dialog').dialog('close');
			// 树形结构的表格
			$('#grid').treegrid({
				url : '${ctx}${adminPath}/area/get-tree',
				fit : true,
				rownumbers : true,
				nowrap : false,
				idField : 'id',
				treeField : 'name',
				toolbar : '#toolbar',
				columns : [ [ {
					title : '区域名称',
					field : 'name',
					width:'30%'
				}, {
					title : '区域类型',
					field : 'type',
					width:'10%'
				}, {
					title : '区域编码',
					field : 'code',
					width:'10%'
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
				url : '${ctx}${adminPath}/area/get-tree',
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
								showType:'fade',
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
					remark : row.remark
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
					url : '${ctx}${adminPath}/area/del-by-id',
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
								showType:'fade',
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