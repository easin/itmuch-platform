package com.itmuch.core.log;

import java.util.List;

/**
 * 分页数据类
 * 
 * @author hk
 * 
 *         2012-10-26 下午8:23:15
 */
public class Pagination {

    /**
     * 一页数据默认20条
     */
    private int pageSize = 20;
    /**
     * 当前页码
     */
    private int curPage;

    /**
     * 上一页
     */
    private int upPage;

    /**
     * 下一页
     */
    private int nextPage;
    /**
     * 一共有多少条数据
     */
    private long total;

    /**
     * 一共有多少页
     */
    private int totalPage;
    /**
     * 数据集合
     */
    private List<Log> rows;

    /**
     * 分页的url
     */
    private String pageUrl;

    /**
     * 获取第一条记录位置
     * 
     * @return
     */
    public int getFirstResult() {
        return (this.getCurPage() - 1) * this.getPageSize();
    }

    /**
     * 获取最后记录位置
     * 
     * @return
     */
    public int getLastResult() {
        return this.getCurPage() * this.getPageSize();
    }

    /**
     * 计算一共多少页
     */
    public void setTotalPage() {
        this.totalPage = (int) (((this.total % this.pageSize) > 0) ? ((this.total / this.pageSize) + 1) : this.total / this.pageSize);
    }

    /**
     * 设置 上一页
     */
    public void setUpPage() {
        this.upPage = (this.curPage > 1) ? this.curPage - 1 : this.curPage;
    }

    /**
     * 设置下一页
     */
    public void setNextPage() {
        this.nextPage = (this.curPage == this.totalPage) ? this.curPage : this.curPage + 1;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public int getUpPage() {
        return this.upPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Pagination(int pageNo, int pageSize, long totalCount2) {
        this.setCurPage(pageNo);
        this.setPageSize(pageSize);
        this.setTotal(totalCount2);
        this.init();
    }

    public int getCurPage() {
        return this.curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Log> getRows() {
        return this.rows;
    }

    public void setRows(List<Log> rows) {
        this.rows = rows;
    }

    public void setUpPage(int upPage) {
        this.upPage = upPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 初始化计算分页
     */
    private void init() {
        this.setTotalPage();// 设置一共页数
        this.setUpPage();// 设置上一页
        this.setNextPage();// 设置下一页
    }

}