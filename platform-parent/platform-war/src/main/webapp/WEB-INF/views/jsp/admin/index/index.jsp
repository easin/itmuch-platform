<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页 - 后台管理</title>
</head>
<body>
	<%--顶部区域 --%>
	<div data-options="region:'north',split:true,border:false" style="height: 60px;padding: 10px;">
		<%@ include file="/WEB-INF/layouts/back/header.jsp"%>
	</div>
	<%--左侧区域 --%>
	<div data-options="region:'west',split:true,title:'导航'" style="width: 200px; padding: 10px;">
		<div id="tree"></div>
	</div>
	<%--底部区域 --%>
	<div data-options="region:'south',border:false" style="height: 30px; padding: 10px; text-align: center;">
		<%@ include file="/WEB-INF/layouts/back/footer.jsp"%>
	</div>
	<%--中间区域 --%>
	<div data-options="region:'center'">
		<div id="mainTabs">
			<div title="欢迎页">
				<iframe src="${ctx }/admin/welcome" allowTransparency="true" style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>



	<script type="text/javascript">
		$(function() {
			$('#tree').tree({
				url : '${ctx}${adminPath}/menu/get-tree-by-user',
				method : 'GET',
				width : '155px',
				onClick : function(node) {
					if (node.href != null && node.href != '') {
						// $('#mainTabs').attr('src',node.href);
						addTab(node.name, node.href);
					}
				}
			});
		});

		function addTab(title, url) {
			if ($('#mainTabs').tabs('exists', title)) {
				$('#mainTabs').tabs('select', title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
				$('#mainTabs').tabs('add', {
					title : title,
					content : content,
					closable : true
				});
			}
		}

		var mainTab;

		$(function() {
			mainTabs = $('#mainTabs').tabs({
				fit : true,
				border : false,
				tools : [ {
					text : '刷新',
					iconCls : 'icon-reload',
					handler : function() {
						var panel = mainTabs.tabs('getSelected').panel('panel');
						var frame = panel.find('iframe');
						try {
							if (frame.length > 0) {
								for (var i = 0; i < frame.length; i++) {
									frame[i].contentWindow.document.write('');
									frame[i].contentWindow.close();
									frame[i].src = frame[i].src;
								}
								if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
									try {
										CollectGarbage();
									} catch (e) {
									}
								}
							}
						} catch (e) {
						}
					}
				}, {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						var index = mainTabs.tabs('getTabIndex', mainTabs.tabs('getSelected'));
						var tab = mainTabs.tabs('getTab', index);
						if (tab.panel('options').closable) {
							mainTabs.tabs('close', index);
						} else {
							$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
						}
					}
				} ]
			});
		});
	</script>




</body>
</html>