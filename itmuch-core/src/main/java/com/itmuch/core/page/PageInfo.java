/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.itmuch.core.page;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.Page;

/**
 * 对Page<E>结果进行包装
 * <p/>
 * 新增分页的多项属性，主要参考:http://bbs.csdn.net/topics/360010907
 *
 * @author liuzh/abel533/isea533
 * @version 3.3.0
 * @since 3.2.2
 * 项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageInfo<T> implements Serializable {
    public void setList(List<T> list) {
        this.list = list;
    }

    private static final long serialVersionUID = 1L;
    private final Boolean success = true;
    //当前页
    @JsonProperty("curPage")
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;
    //由于startRow和endRow不常用，这里说个具体的用法
    //可以在页面中"显示startRow到endRow 共size条数据"

    //当前页面第一个元素在数据库中的行号
    private int startRow;
    //当前页面最后一个元素在数据库中的行号
    private int endRow;
    //总记录数
    private long total;

    public void setTotal(long total) {
        this.total = total;
    }

    //总页数
    private int pages;
    //结果集
    @JsonProperty("rows")
    private List<T> list;

    //第一页
    private int firstPage;
    //前一页
    private int prePage;
    //下一页
    private int nextPage;
    //最后一页
    private int lastPage;

    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;
    //是否有前一页
    private boolean hasPreviousPage = false;
    //是否有下一页
    private boolean hasNextPage = false;
    //导航页码数
    private int navigatePages;
    //所有导航页号
    private int[] navigatepageNums;

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        this(list, 8);
    }

    /**
     * 包装Page对象
     *
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageInfo(List<T> list, int navigatePages) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
            //由于结果是>startRow的，所以实际的需要+1
            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                //计算实际的endRow（最后一页的时候特殊）
                this.endRow = (this.startRow - 1) + this.size;
            }
            this.navigatePages = navigatePages;
            //计算导航页
            this.calcNavigatepageNums();
            //计算前后页，第一页，最后一页
            this.calcPage();
            //判断页面边界
            this.judgePageBoudary();
        }
    }

    /**
     * 计算导航页
     */
    private void calcNavigatepageNums() {
        //当总页数小于或等于导航页码数时
        if (this.pages <= this.navigatePages) {
            this.navigatepageNums = new int[this.pages];
            for (int i = 0; i < this.pages; i++) {
                this.navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            this.navigatepageNums = new int[this.navigatePages];
            int startNum = this.pageNum - (this.navigatePages / 2);
            int endNum = this.pageNum + (this.navigatePages / 2);

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < this.navigatePages; i++) {
                    this.navigatepageNums[i] = startNum++;
                }
            } else if (endNum > this.pages) {
                endNum = this.pages;
                //最后navigatePages页
                for (int i = this.navigatePages - 1; i >= 0; i--) {
                    this.navigatepageNums[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < this.navigatePages; i++) {
                    this.navigatepageNums[i] = startNum++;
                }
            }
        }
    }

    /**
     * 计算前后页，第一页，最后一页
     */
    private void calcPage() {
        if ((this.navigatepageNums != null) && (this.navigatepageNums.length > 0)) {
            this.firstPage = this.navigatepageNums[0];
            this.lastPage = this.navigatepageNums[this.navigatepageNums.length - 1];
            if (this.pageNum > 1) {
                this.prePage = this.pageNum - 1;
            }
            if (this.pageNum < this.pages) {
                this.nextPage = this.pageNum + 1;
            }
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        this.isFirstPage = this.pageNum == 1;
        this.isLastPage = this.pageNum == this.pages;
        this.hasPreviousPage = this.pageNum > 1;
        this.hasNextPage = this.pageNum < this.pages;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getSize() {
        return this.size;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public long getTotal() {
        return this.total;
    }

    public int getPages() {
        return this.pages;
    }

    public List<T> getList() {
        return this.list;
    }

    public int getFirstPage() {
        return this.firstPage;
    }

    public int getPrePage() {
        return this.prePage;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public int getLastPage() {
        return this.lastPage;
    }

    public boolean isIsFirstPage() {
        return this.isFirstPage;
    }

    public boolean isIsLastPage() {
        return this.isLastPage;
    }

    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    public int getNavigatePages() {
        return this.navigatePages;
    }

    public int[] getNavigatepageNums() {
        return this.navigatepageNums;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", pageSize=").append(this.pageSize);
        sb.append(", size=").append(this.size);
        sb.append(", startRow=").append(this.startRow);
        sb.append(", endRow=").append(this.endRow);
        sb.append(", total=").append(this.total);
        sb.append(", pages=").append(this.pages);
        sb.append(", list=").append(this.list);
        sb.append(", firstPage=").append(this.firstPage);
        sb.append(", prePage=").append(this.prePage);
        sb.append(", nextPage=").append(this.nextPage);
        sb.append(", lastPage=").append(this.lastPage);
        sb.append(", isFirstPage=").append(this.isFirstPage);
        sb.append(", isLastPage=").append(this.isLastPage);
        sb.append(", hasPreviousPage=").append(this.hasPreviousPage);
        sb.append(", hasNextPage=").append(this.hasNextPage);
        sb.append(", navigatePages=").append(this.navigatePages);
        sb.append(", navigatepageNums=");
        if (this.navigatepageNums == null) {
            sb.append("null");
        } else {
            sb.append('[');
            for (int i = 0; i < this.navigatepageNums.length; ++i) {
                sb.append(i == 0 ? "" : ", ").append(this.navigatepageNums[i]);
            }
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }

    public Boolean getSuccess() {
        return this.success;
    }
}
