<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ECharts DEMO</title>
</head>
<body>
	<div id="lineChart" style="height: 400px"></div>


	<script type="text/javascript" src="${ctx }/static/scripts/jquery-1.11.1.js"></script>


	<script src="${ctx }/static/scripts/echarts-2.2.7/build/dist/echarts.js"></script>
	<script type="text/javascript">
		$.ajax({
			url : '${ctx}/demo/echarts/option',
			dataType : "json",
			type : "GET",
			contentType : "application/json;charset=utf-8",
			success : function(data) {
				// 路径配置
				require.config({
					paths : {
						echarts : '${ctx }/static/scripts/echarts-2.2.7/build/dist',
						// 主题设置
						blue : '${ctx}/static/scripts/echarts-2.2.7/doc/example/theme/macarons'
					}
				});
				// 使用柱状图就加载bar模块，按需加载, 注意blue的位置.
				require([ 'echarts', 'blue', 'echarts/chart/bar', 'echarts/chart/line' ], function(ec, theme) {
					var lineChart = ec.init(document.getElementById('lineChart'), theme);

					// 配置的各种选项,由后台生成.
					var barOption = data;

					// 为echarts对象加载数据
					lineChart.setOption(barOption, 'blue');
				});
			},
			error : function() {
				$.messager.alert("操作提示", "操作失败！", "error");
			}
		});
	</script>
</body>
</html>