package com.itmuch.platform.admin.domain;

import java.util.Date;

import com.itmuch.core.entity.BaseEntity;

public class Menu extends BaseEntity {
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
     * 链接.
     */
    private String href;

    /**
     * 目标.
     */
    private String target;

    /**
     * 图标.
     */
    private String icon;

    /**
     * 是否在菜单中显示 0:否 1:是.
     */
    private Byte showFlag;

    /**
     * 权限标识.
     */
    private String permission;

    /**
     * 创建者.
     */
    private Integer createId;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新者.
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

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getShowFlag() {
        return this.showFlag;
    }

    public void setShowFlag(Byte showFlag) {
        this.showFlag = showFlag;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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