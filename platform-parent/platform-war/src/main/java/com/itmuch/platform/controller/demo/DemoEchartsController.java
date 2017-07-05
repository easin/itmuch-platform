package com.itmuch.platform.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Line;

@Controller
@RequestMapping("/demo/echarts")
public class DemoEchartsController {
    /**
     * echart demo页面 
     * 参考: http://git.oschina.net/itmuch/Tools-EChart-Guidelines
     * @see http://git.oschina.net/itmuch/Tools-EChart-Guidelines
     * @return 页面
     */
    @RequestMapping("")
    public String index() {
        return "demo/echarts/index";
    }

    /**
     * echarts demo
     * 更多options demo见: http://git.oschina.net/free/ECharts 里面的单元测试类
     * @see http://git.oschina.net/free/ECharts
     * @return echarts配置项
     */
    @RequestMapping("/option")
    @ResponseBody
    public Option echartsDemo() {
        Option option = new Option();
        option.title("某楼盘销售情况", "纯属虚构");
        option.tooltip().trigger(Trigger.axis);
        option.legend("意向", "预购", "成交");
        option.toolbox().show(true)
                .feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled), Tool.restore, Tool.saveAsImage)
                .padding(20);
        option.calculable(true);
        option.xAxis(new CategoryAxis().boundaryGap(false).data("周一", "周二", "周三", "周四", "周五", "周六", "周日"));
        option.yAxis(new ValueAxis());

        Line l1 = new Line("成交");
        l1.smooth(true).itemStyle().normal().areaStyle().typeDefault();
        l1.data(10, 12, 21, 54, 260, 830, 710);

        Line l2 = new Line("预购");
        l2.smooth(true).itemStyle().normal().areaStyle().typeDefault();
        l2.data(30, 182, 434, 791, 390, 30, 10);

        Line l3 = new Line("意向");
        l3.smooth(true).itemStyle().normal().areaStyle().typeDefault();
        l3.data(1320, 1132, 601, 234, 120, 90, 20);

        option.series(l1, l2, l3);

        return option;
    }
}
