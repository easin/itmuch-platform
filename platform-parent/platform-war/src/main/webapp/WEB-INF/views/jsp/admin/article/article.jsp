<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>友情链接管理</title>
</head>
<body>

	<%-- 工具栏 --%>
	<div id="toolbar" style="height: auto">
		<shiro:hasPermission name="cms:article:insert">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="add()">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="cms:article:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="cms:article:del">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteById()">删除</a>
		</shiro:hasPermission>
	</div>
	<table id="grid"></table>



	<div id="dialog" class="easyui-dialog" title="添加友情链接" data-options="iconCls:'icon-save'" style="border: 0; width: 500px;">
		<form id="mainForm" method="post">
			<input name="id" type="hidden">
			<table cellpadding="5">
				<tr>
					<td>所属栏目:</td>
					<td><input name="categoryId" id="categoryId" class="easyui-combotree" style="width: 155px;" data-options="required:true"></td>
				</tr>
				<tr>
					<td>标题:</td>
					<td><input class="easyui-textbox" type="text" name="title" data-options="required:true"></td>
				</tr>
				<tr>
					<td>推荐位置:</td>
					<td><select name="posid" id="posid">
							<option value="1">首页焦点图</option>
							<option value="2">内页推荐</option>
					</select></td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input class="easyui-textbox" name="remarks" data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
				<tr>
					<td>内容:</td>
					<td><input class="easyui-textbox" name="content" data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/article/insert')" id="addSub">添加提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="submitForm('${ctx}${adminPath}/article/edit-by-id')" id="editSub">编辑提交</a> <a href="javascript:void(0)" class="easyui-linkbutton"
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
				url : '${ctx}${adminPath}/article/list-paged',
				fit : true,
				rownumbers : true,
				nowrap : false,
				striped : true,
				pagination : true,
				singleSelect : true,
				idField : 'id',
				sortName : 'createDate',
				sortOrder : 'desc',
				pageSize : 20,
				pageList : [ 10, 20, 30, 50 ],
				columns : [ [ {
					title : '标题',
					field : 'title',
					width : '10%'
				}, {
					title : '所属栏目',
					field : 'categoryName',
					width : '15%'
				}, {
					title : '创建时间',
					field : 'createDate',
					width : '15%'
				}, {
					title : '修改时间',
					field : 'updateDate',
					width : '15%'
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

			// 弹出框加载树形
			$('#categoryId').combotree({
				url : '${ctx}${adminPath}/category/get-tree',
				method : 'GET'
			});

			$('#posid').combobox({
				multiple : true,
				panelHeight : 'auto',
				width : '155px',
				required : true
			});

		});

		// 提交form 
		function submitForm(url) {
			if ($('#mainForm').form('validate')) {
				var formJson = $('#mainForm').find('input').not('[value=""]').serializeJSON();
				var posidArr = $("#posid").combotree("getValues");
				var posid = posidArr.join(',');
				formJson.posid = posid;
				// alert(JSON.stringify(formJson));
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
			$('#password').textbox({
				required : true
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
					required : false
				});
				if (row.photo != '' && row.photo != null) {
					$('#photoImg').attr('src', '${ctx}' + '/' + row.photo).show();
				}
				$('#addSub').hide();
				$('#editSub').show();

				// 加载到form表单中
				var id = row.id;
				$.ajax({
					url : '${ctx}${adminPath}/article/get-by-id/' + id,
					dataType : "json",
					type : "POST",
					contentType : "application/json;charset=utf-8",
					success : function(data) {
						if (data.data != "" && data.data != null) {
							content = data.data.content;
							$('#mainForm').form('load', {
								id : id,
								categoryId : data.data.categoryId,
								title : data.data.title,
								content : data.data.content,
								remarks : data.data.remarks
							});
						}
					},
					error : function() {
						$.messager.alert("操作提示", "操作失败！", "error");
					}
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
					url : '${ctx}${adminPath}/article/del-by-id',
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