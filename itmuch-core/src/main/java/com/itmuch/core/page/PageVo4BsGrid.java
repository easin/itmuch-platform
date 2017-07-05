package com.itmuch.core.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class PageVo4BsGrid {
    //pageSize=500&curPage=1&sortName=XH,ID&sortOrder=asc,desc
    /**
     * 单页展示多少条
     */
    private Integer pageSize = 10;
    /**
     * 页码.
     */
    private Integer curPage = 1;
    /**
     * 排序字段名称.
     */
    private String sortName;
    /**
     * 排序方式.
     */
    private String sortOrder;

    /**
     * 获取最终的排序字符串
     * @param columns 允许排序的字段
     * @return 排序字符串
     */
    public String getOrder(String columns) {
        StringBuffer orderBuffer = new StringBuffer();
        if (!StringUtils.isEmpty(this.sortName)) {

            String[] names = this.sortName.trim().split(",");
            this.sortOrder = !StringUtils.isEmpty(this.sortOrder) ? this.sortOrder : "";
            String[] orders = this.sortOrder.trim().split(",");
            List<String> namesList = Arrays.asList(names);
            List<String> ordersList = Arrays.asList(orders);
            List<String> ordersList2 = new ArrayList<String>();
            ordersList2.addAll(ordersList);
            for (int i = 0; i < (namesList.size() - ordersList.size()); i++) {
                ordersList2.add("ASC");
            }

            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < names.length; i++) {
                map.put(names[i], ordersList2.get(i));
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

    public static void main(String[] args) {
        PageVo4BsGrid vo = new PageVo4BsGrid();
        vo.setSortName(",");
        vo.setSortOrder(",DESC");

        //        List<String> list = new ArrayList<String>();
        //        list.add("id");
        //        list.add("super");
        //        list.add("te");
        String string = vo.getOrder("id,super,te");
        System.out.println(string);
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurPage() {
        return this.curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
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
