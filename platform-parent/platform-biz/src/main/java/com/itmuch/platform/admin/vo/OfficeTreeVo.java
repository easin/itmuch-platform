package com.itmuch.platform.admin.vo;

import java.util.Date;

import com.itmuch.core.entity.TreeEntity;

public class OfficeTreeVo extends TreeEntity<OfficeTreeVo> {
    private static final long serialVersionUID = 1L;

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

    private String areaName;

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

    private String text;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


}
