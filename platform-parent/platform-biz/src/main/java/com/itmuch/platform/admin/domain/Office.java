package com.itmuch.platform.admin.domain;

import java.util.Date;

import com.itmuch.core.entity.BaseEntity;

public class Office extends BaseEntity {
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
     * 归属区域.
     */
    private Integer areaId;

    /**
     * 区域编码.
     */
    private String code;

    /**
     * 机构类型.
     */
    private Byte type;

    /**
     * 联系地址.
     */
    private String address;

    /**
     * 邮政编码.
     */
    private String zipCode;

    /**
     * 负责人.
     */
    private String master;

    /**
     * 电话.
     */
    private String phone;

    /**
     * 传真.
     */
    private String fax;

    /**
     * 邮箱.
     */
    private String email;

    /**
     * 状态 1:正常 0:无用.
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

    public Integer getAreaId() {
        return this.areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMaster() {
        return this.master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}