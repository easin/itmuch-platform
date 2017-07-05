<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理</title>
</head>
<body>

	<%-- 工具栏 --%>
	<div id="toolbar" style="height: auto">
		<shiro:hasPermission name="sys:user:insert">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add()">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:del">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteById()">删除</a>
		</shiro:hasPermission>
	</div>
	<table id="grid"></table>



	<div id="dialog" class="easyui-dialog" title="添加用户" data-options="iconCls:'icon-save'" style="border: 0; width: 500px;">
		<form id="mainForm" method="post">
			<input name="id" type="hidden">
			<table cellpadding="5">
				<tr>
					<td>头像:</td>
					<td><img id="photoImg" style="display: none; width: 100px; height: 100px"> <input type="hidden" name="photo" id="photo" /> <input type="file" name="file"
						id="file_upload" /></td>
					<td>归属公司:</td>
					<td><input name="companyId" id="companyId" class="easyui-combotree" data-options="required:true" style="width: 155px;"></td>
				</tr>
				<tr>
					<td>归属部门:</td>
					<td><input name="officeId" id="officeId" class="easyui-combotree" data-options="required:true" style="width: 155px;"></td>
					<td>用户类型:</td>
					<td><select id="type" name="type" class="easyui-combobox" data-options="required:true,panelHeight:'auto'">
							<option value="0">普通用户</option>
							<option value="1">系统管理</option>
					</select></td>
				</tr>
				<tr>
					<td>用户角色:</td>
					<td><select name="roleIds" id="roleIds"></select></td>
					<td>姓名:</td>
					<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>用户名:</td>
					<td><input class="easyui-textbox" type="text" name="username" data-options="required:true"></input></td>
					<td>密码:</td>
					<td><input class="easyui-textbox" type="password" name="password" id="password"></input></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input class="easyui-textbox" type="text" name="email" data-options="validType:'email'"></input></td>
					<td>电话:</td>
					<td><input class="easyui-textbox" type="text" name="phone"></input></td>
				</tr>

				<tr>
					<td>手机:</td>
					<td><input class="easyui-textbox" type="text" name="mobile"></input></td>
					<td>备注:</td>
					<td><input class="easyui-textbox" name="remark" data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/user/insert')" id="addSub">添加提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/user/edit-by-id')" id="editSub">编辑提交</a> <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="closeForm()">取消</a>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/static/scripts/jquery.serializeJSON-2.6.1/jquery.serializejson.js"></script>
	<script type="text/javascript" src="${ctx }/static/scripts/uploadify/js/jquery.uploadify.js"></script>
	<link href="${ctx }/static/scripts/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		$(function() {
			$('#dialog').dialog('close');
			var grid = $('#grid').datagrid({
				title : '',
				url : '${ctx}${adminPath}/user/list-paged',
				fit : true,
				rownumbers : true,
				nowrap : false,
				striped : true,
				pagination : true,
				singleSelect : true,
				idField : 'id',
				sortName : 'createTime',
				sortOrder : 'desc',
				pageSize : 20,
				pageList : [ 10, 20, 30, 50 ],
				columns : [ [ {
					title : '姓名',
					field : 'name',
					width: '10%'
				}, {
					title : '用户名',
					field : 'username',
					width: '15%'
				}, {
					title : '归属公司',
					field : 'companyName',
					width: '10%'
				}, {
					title : '归属部门',
					field : 'officeName',
					width: '10%'
				}, {
					title : '邮箱',
					field : 'email',
					width: '15%'
				}, {
					title : '电话',
					field : 'phone',
					width: '15%'
				}, {
					title : '创建时间',
					field : 'createTime',
					width: '20%',
					sortable : true
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

			// 选择公司
			$('#companyId').combotree({
				url : '${ctx}${adminPath}/office/get-tree-by-type?type=1',
				method : 'GET'
			});

			// 选择角色
			$('#roleIds').combobox({
				url : '${ctx}${adminPath}/role/list-all',
				method : 'get',
				valueField : 'id',
				textField : 'name',
				multiple : true,
				panelHeight : 'auto',
				width : '155px',
				required:true
			});
			// 选择部门
			$('#officeId').combotree({
				url : '${ctx}${adminPath}/office/get-tree-by-type?type=2',
				onBeforeSelect : function(node) {
					// 如果没有选中子节点, 弹出提示
					if (!$(this).tree('isLeaf', node.target)) {
						$.messager.alert("操作提示", '请选择叶子节点！', "error");
						return false;
					}
				},
				onClick : function(node) {
					if (!$(this).tree('isLeaf', node.target)) {
						$('#cc').combo('showPanel');
					}
				}
			});
			// combotree 结束
		});

		// 提交form 
		function submitForm(url) {
			if ($('#mainForm').form('validate')) {
				var formJson = $('#mainForm').find('input').not('[value=""]')
						.serializeJSON();
				var roleIds = $("#roleIds").combotree("getValues");
				formJson.roleIds = roleIds;

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
							$('#grid').datagrid('reload');
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
			$('#password').textbox({
				required:true
			});
			$('#photoImg').hide();
			$('#addSub').show();
			$('#editSub').hide();
		};

		// 修改
		function edit() {
			var row = $('#grid').datagrid('getSelected');
			//如果选中了
			if (row) {
				$('#dialog').dialog('open');
				$('#password').textbox({
					required:false
				});
				if (row.photo != '' && row.photo != null) {
					$('#photoImg').attr('src', '${ctx}' + '/' + row.photo)
							.show();
				}
				$('#addSub').hide();
				$('#editSub').show();
				// alert(row.id);
				// 加载到form表单中
				$('#mainForm').form('load', {
					id : row.id,
					phone : row.phone,
					companyId : row.companyId,
					officeId : row.officeId,
					userType : row.userType,
					roleIds : row.roleIds,
					no : row.no,
					name : row.name,
					username : row.username,
					password : row.password,
					email : row.email,
					mobile : row.mobile,
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
			var row = $('#grid').datagrid('getSelected');
			if (row) {
				$.ajax({
					url : '${ctx}${adminPath}/user/del-by-id',
					dataType : "json",
					data : {
						id : row.id
					},
					type : "GET",
					contentType : "application/json;charset=utf-8",
					success : function(data) {
						if (data.success == true) {
							// 重新加载
							$('#grid').datagrid('reload');
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


	<script type="text/javascript">
		$(function() {
			$("#file_upload").uploadify({
				/* 'buttonImage' :'https://www.baidu.com/img/baidu_jgylogo3.gif',
				'buttonClass':'easyui-linkbutton', */
				'buttonText' : '请选择',
				'height' : 20,
				'width' : 120,
				'swf' : '${ctx}/static/scripts/uploadify/uploadify.swf',
				'uploader' : '${ctx}${adminPath}/common/upload',
				'fileTypeExts' : '*.jpg;*.gif;*.png;*.bmp', // 过滤类型
				'fileTypeDesc' : '格式为*.jpg;*.gif;*.png;*.bmp',
				'multi' : false,
				/* 'auto' : false, */
				'fileObjName' : 'file',
				'onUploadSuccess' : function(file, data, response) {
					$('#photo').val(data);
				}
			});
		});
	</script>
</body>
</html>