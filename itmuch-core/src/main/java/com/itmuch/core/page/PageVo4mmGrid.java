package com.itmuch.core.page;

public class PageVo4mmGrid {
    /**
     * 当前页码
     */
    private Integer page = 1;
    /**
     * 单页显示多少条
     */
    private Integer limit = 10;

    /**
     * 排序
     */
    private String sort;

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
