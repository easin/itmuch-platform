package com.itmuch.platform.admin.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itmuch.platform.admin.domain.Office;

public class RoleVo {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    private Office office;

    private List<Integer> menuIds;

    public Integer getId() {
        return this.id;
    }

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

    public Office getOffice() {
        return this.office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public List<Integer> getMenuIds() {
        return this.menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

}