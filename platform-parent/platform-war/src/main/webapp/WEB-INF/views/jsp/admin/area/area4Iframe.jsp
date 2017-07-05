<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />

<title>区域管理</title>
</head>
<body>
	
	<%-- 工具栏 --%>
	<div id="toolbar" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" id="add" >添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit" onclick="edit()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteById()">删除</a>
	</div>
	<table id="treeTable"></table>



	<div id="dialog" style="display: none">
		<iframe id="mainIframe" name="mainIframe" src="${ctx }/admin/area/add"  style="border: 0;width: 100%;height: 100%" application="yes"></iframe>
		
	</div>
	<script type="text/javascript" src="${ctx }/static/scripts/jquery.serializeJSON-2.6.1/jquery.serializejson.js"></script>
	<script type="text/javascript">
		$(function() {
			
			
			
			// 树形结构的表格
			$('#treeTable').treegrid({
				url : '${ctx}${adminPath}/area/get-tree',
				idField : 'id',
				treeField : 'name',
				toolbar: '#toolbar',
				columns : [ [ {
					title : 'id',
					field : 'id'
				}, {
					title : '区域名称',
					field : 'name'
				}, {
					title : '区域类型',
					field : 'typeName'
				}, {
					title : '区域编码',
					field : 'code'
				} ] ]
			});
			// 结束树形表
			// 添加
			$('#add').click(function() {
				$('#dialog').show();
				$('#dialog').dialog({
					title: "添加",
					iconCls:'icon-save',
					width:'500px',
					height:'400px'
				});
				// 一旦清空,那select默认就没有选中值了
				// $('#mainForm').form('clear');
				 $('#addSub').show();
				 $('#editSub').hide();
			});

			

		});


		
		// 修改
		function edit(){
			var row = $('#treeTable').treegrid('getSelected');
			//如果选中了
			if(row){
				
				
				alert(row.id);
				// 加载到form表单中
				 var form = $(window.frames["mainIframe"].document).find("#mainForm");
				alert(JSON.stringify(form));
				 var form2 = $('#mainIframe').contents().find("#mainForm");
				alert(JSON.stringify(form2));
				
				form.form('load',{
					id:row.id,
					parentId:row.parentId,
					name:row.name,
					code:row.code,
					remarks:row.remarks
				});
				
				 $('#dialog').dialog({
					title: "添加",
					iconCls:'icon-save',
					width:'500px',
					height:'400px'
				});
				
			}
			//没选中
			else{
				$.messager.alert("操作提示", "请先选中一行！", "error");
			}
		}
		// 修改结束
		function deleteById(){
			var row = $('#treeTable').treegrid('getSelected');
			if(row){
				$.ajax({
					url : '${ctx}${adminPath}/area/del-by-id',
					dataType : "json",
					data : {id:row.id},
					type : "GET",
					contentType : "application/json;charset=utf-8",
					success : function(data) {
						if (data.success == true) {
							// 重新加载
							$('#treeTable').treegrid('reload');
							$.messager.show({
								title : '删除成功',
								msg : '删除区域信息成功.',
								timeout : 5000
							});
						} else {
							$.messager.alert("操作提示", data.msg, "error");
						}
					},
					error : function() {
						$.messager.alert("操作提示", "操作失败！", "error");
					}
				});
			}
			else{
				$.messager.alert("操作提示", "请先选中一行！", "error");
			}
		}
	</script>
</body>
</html>