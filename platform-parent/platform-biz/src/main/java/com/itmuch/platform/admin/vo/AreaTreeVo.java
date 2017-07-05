package com.itmuch.platform.admin.vo;

import java.util.Date;

import com.itmuch.core.entity.TreeEntity;

public class AreaTreeVo extends TreeEntity<AreaTreeVo> {
    private static final long serialVersionUID = 1L;

    /**
     * 名称.
     */
    private String name;

    /**
     * 排序.
     */
    private Long sort;

    /**
     * 区域编码.
     */
    private String code;

    /**
     * 区域类型.
     */
    private String type;

    /**
     * 创建者.
     */
    private String createBy;

    /**
     * 创建时间.
     */
    private Date createDate;

    /**
     * 更新者.
     */
    private String updateBy;

    /**
     * 更新时间.
     */
    private Date updateDate;

    /**
     * 备注信息.
     */
    private String remarks;
    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 删除标记.
     */
    private String delFlag;

    private String typeName;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSort() {
        return this.sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}