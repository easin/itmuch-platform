<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理</title>
</head>
<body>

	<%-- 工具栏 --%>
	<div id="toolbar" style="height: auto">
		<shiro:hasPermission name="sys:role:insert">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add()">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:role:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:role:del">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteById()">删除</a>
		</shiro:hasPermission>
	</div>
	<table id="grid"></table>



	<div id="dialog" class="easyui-dialog" title="添加角色" data-options="iconCls:'icon-save'" style="border: 0; width: 500px;">
		<form id="mainForm" method="post">
			<input name="id" type="hidden">
			<table cellpadding="5">
				<tr>
					<td>归属机构:</td>
					<td><input name="officeId" id="officeId" class="easyui-combotree" style="width: 155px;" data-options="required:true"></td>
				</tr>
				<tr>
					<td>角色名称:</td>
					<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>英文名称:</td>
					<td><input class="easyui-textbox" type="text" name="enname" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>角色类型:</td>
					<td><select id="type" name="roleType" class="easyui-combobox" data-options="required:true,panelHeight:'auto'">
							<option value="1">管理角色</option>
							<option value="2">普通角色</option>
					</select></td>
				</tr>
				<tr>
					<td>分配权限:</td>
					<td><input name="menuIds" id="menuIds" class="easyui-combotree" style="width: 155px;"></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input class="easyui-textbox" name="remarks" data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/role/insert')" id="addSub">添加提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/role/edit-by-id')" id="editSub">编辑提交</a> <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="closeForm()">取消</a>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/static/scripts/jquery.serializeJSON-2.6.1/jquery.serializejson.js"></script>
	<script type="text/javascript">
		$.ajaxSetup({
			cache : false
		//关闭AJAX相应的缓存
		});

		$(function() {
			$('#dialog').dialog('close');
			/**
			 *在点击排序字段时，改变传入后台的字段
			 *param对应onBeforeLoad事件的参数
			 *map自定义的字段映射Map
			 */
			onSortColumn = function(param, map) {
				//取出map中字段的映射关系值
				var fieldSort = map[param.sort];
				if (fieldSort != '' && fieldSort != undefined) {
					//设置新的排序字段名，设置完之后，发送请求时一并会发送到服务端
					param.sort = fieldSort;
				}
			}
			//创建Map
			var map = new Object();
			map['createDate'] = 'a.create_date';
			var grid = $('#grid').datagrid({
				title : '',
				url : '${ctx}${adminPath}/role/list-paged',
				fit : true,
				rownumbers : true,
				nowrap : false,
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				idField : 'id',
				sortName : 'createDate',
				sortOrder : 'desc',
				pageSize : 20,
				pageList : [ 10, 20, 30, 50 ],
				columns : [ [ {
					title : '角色名称',
					field : 'name',
					width: '30%'
				}, {
					title : '所属机构',
					field : 'office',
					formatter : function(value, rec) {
						// alert(JSON.stringify(rec));
						var office = rec.office;
						if (office != null) {
							return office.name;
						}
					},
					width: '30%'
				}, {
					title : '创建时间',
					field : 'createTime',
					sortable : true,
					width: '30%'
				} ] ],
				toolbar : '#toolbar',
				onBeforeLoad : function(param) {

					onSortColumn(param, map);

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

			// 弹出框加载地区的树形
			$('#officeId').combotree({
				url : '${ctx}${adminPath}/office/get-tree',
				method : 'GET'
			});

			$('#menuIds').combotree({
				url : '${ctx}${adminPath}/menu/get-tree',
				multiple : true,
				method : 'GET',
				cascadeCheck : false,
				onChange : function(newVal, oldVal) {
					var newString = newVal.toString();
					var oldString = oldVal.toString();
					var tree = $('#menuIds').combotree('tree');
					// 节点被勾选时, 自动勾选父节点
					if (newVal.length > oldVal.length) {
						for (var i = 0; i < newVal.length; i++) {
							// 新增的节点
							if (oldString.indexOf(newVal[i]) <= -1) {
								// 获得当前被操作的节点
								var item = tree.tree('find', newVal[i]);
								// alert(item.text);
								var parent = item;
								while (parent != null) {
									var parent = tree.tree('getParent', parent.target);
									if (parent != null) {
										tree.tree('check', parent.target);
									}
								}
							}
						}
					}
					// 节点取消勾选时, 自动取消子节点
					else if (newVal.length < oldVal.length) {
						for (var i = 0; i < oldVal.length; i++) {
							if (newString.indexOf(oldVal[i]) <= -1) {
								// 获得当前被操作的节点
								var item = tree.tree('find', oldVal[i]);
								var children = tree.tree('getChildren', item.target);
								if (children.length != 0) {
									for (var i = 0; i < children.length; i++) {
										tree.tree('uncheck', children[i].target);
									}
								}
							}
						}
					} else {

					}
				}
			});
			// 弹出框树形加载结束
		});

		// 提交form 
		function submitForm(url) {
			if ($('#mainForm').form('validate')) {
				var formJson = $('#mainForm').find('input').not('[value=""]').serializeJSON();
				// alert(JSON.stringify(formJson))
				var menuIds = $("#menuIds").combotree("getValues");
				formJson.menuIds = menuIds;
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
			var row = $('#grid').datagrid('getSelected');
			//如果选中了
			if (row) {
				$('#dialog').dialog('open');
				$('#addSub').hide();
				$('#editSub').show();
				// alert(row.id);
				// 加载到form表单中
				$('#mainForm').form('load', {
					id : row.id,
					officeId : row.officeId,
					name : row.name,
					enname : row.enname,
					roleType : row.roleType,
					menuIds : row.menuIds,
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
			var row = $('#grid').datagrid('getSelected');
			if (row) {
				$.ajax({
					url : '${ctx}${adminPath}/role/del-by-id',
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