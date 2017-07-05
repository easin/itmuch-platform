package com.itmuch.platform.admin.domain;

import java.util.Date;

import com.itmuch.core.entity.BaseEntity;

public class Role extends BaseEntity {
    /**
     * 编号.
     */
    private Integer id;

    /**
     * 归属机构.
     */
    private Integer officeId;

    /**
     * 角色名称.
     */
    private String name;

    /**
     * 英文名称.
     */
    private String enname;

    /**
     * 角色类型.
     */
    private Byte roleType;

    /**
     * 是否系统数据 0否 1是.
     */
    private Byte sysFlag;

    /**
     * 状态 0:可用 1:禁用.
     */
    private Byte status;

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

    public Integer getOfficeId() {
        return this.officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnname() {
        return this.enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public Byte getRoleType() {
        return this.roleType;
    }

    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    public Byte getSysFlag() {
        return this.sysFlag;
    }

    public void setSysFlag(Byte sysFlag) {
        this.sysFlag = sysFlag;
    }

    public Byte getStatus() {
        return this.status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
    private Office office;

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
}