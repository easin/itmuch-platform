package com.itmuch.core.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.itmuch.core.util.StringUtil;

public class PageVo {
    /**
     * 页码
     */
    private Integer page = 1;
    /**
     * 单页显示多少条
     */
    private Integer rows = 10;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序方式
     */
    private String order;

    /**
     * 获取最终的排序字符串
     * @param columns 允许排序的字段
     * @return 排序字符串
     */
    // TODO 待review, 用easyui 应该没这么复杂
    public String getOrder(String columns) {
        StringBuffer orderBuffer = new StringBuffer();
        if (!StringUtils.isEmpty(this.sort)) {

            String[] names = this.sort.trim().split(",");
            this.order = !StringUtils.isEmpty(this.order) ? this.order : "";
            String[] orders = this.order.trim().split(",");
            List<String> namesList = Arrays.asList(names);
            List<String> ordersList = Arrays.asList(orders);
            List<String> ordersList2 = new ArrayList<String>();
            ordersList2.addAll(ordersList);
            for (int i = 0; i < (namesList.size() - ordersList.size()); i++) {
                ordersList2.add("ASC");
            }

            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < names.length; i++) {
                map.put(StringUtil.underscoreName(names[i]), ordersList2.get(i));
            }
            if (!map.isEmpty()) {
                Set<String> keySet = map.keySet();
                // Map<String, String> map
                for (String key : keySet) {
                    String value = map.get(key);
                    String[] columnsArray = columns.split(",");
                    List<String> columnsList = Arrays.asList(columnsArray);
                    if (columnsList.contains(key)) {
                        orderBuffer.append(key).append(" ").append(Direction.fromString(value)).append(",");
                    }
                }
                if (orderBuffer.toString().endsWith(",")) {
                    return orderBuffer.substring(0, orderBuffer.length() - 1);
                }
                return orderBuffer.toString();
            }
        }
        return null;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return this.rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public static enum Direction {
        ASC, DESC;

        private Direction() {
        }

        public static Direction fromString(String value) {
            try {
                return valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
            }
            return ASC;
        }
    }
}
