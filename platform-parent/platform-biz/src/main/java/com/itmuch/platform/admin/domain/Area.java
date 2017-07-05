package com.itmuch.platform.admin.domain;

import java.util.Date;

import com.itmuch.core.entity.BaseEntity;

public class Area extends BaseEntity {
    /**
     * 编号.
     */
    private Integer id;

    /**
     * 父级编号.
     */
    private Integer parentId;

    /**
     * 名称.
     */
    private String name;

    /**
     * 排序.
     */
    private Short sort;

    /**
     * 区域编码.
     */
    private String code;

    /**
     * 区域类型.
     */
    private Byte type;

    /**
     * 创建者id.
     */
    private Integer createId;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新者id.
     */
    private Integer updateId;

    /**
     * 更新时间.
     */
    private Date updateTime;

    /**
     * 备注信息.
     */
    private String remark;

    /**
     * 删除标记.
     */
    private Byte delFlag;

    private static final long serialVersionUID = 1L;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getSort() {
        return this.sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getType() {
        return this.type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public Integer getCreateId() {
        return this.createId;
    }

    @Override
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    @Override
    public Date getCreateTime() {
        return this.createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Integer getUpdateId() {
        return this.updateId;
    }

    @Override
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    @Override
    public Date getUpdateTime() {
        return this.updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getRemark() {
        return this.remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Byte getDelFlag() {
        return this.delFlag;
    }

    @Override
    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }
}