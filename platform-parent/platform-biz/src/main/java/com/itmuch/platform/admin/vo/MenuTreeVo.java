package com.itmuch.platform.admin.vo;

import java.util.Date;

import com.itmuch.core.entity.TreeEntity;

public class MenuTreeVo extends TreeEntity<MenuTreeVo> {
    private static final long serialVersionUID = -7111524913053070340L;

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

    private String text;

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

    public Integer getCreateId() {
        return this.createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return this.updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

}
