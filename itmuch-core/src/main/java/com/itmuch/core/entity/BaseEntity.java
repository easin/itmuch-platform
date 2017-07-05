package com.itmuch.core.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 创建者id.
     */
    protected Integer createId;
    /**
     * 创建时间.
     */
    protected Date createTime;

    /**
     * 更新者id.
     */
    protected Integer updateId;

    /**
     * 更新时间.
     */
    protected Date updateTime;

    /**
     * 备注信息.
     */
    protected String remark;

    /**
     * 删除标记.
     */
    protected Byte delFlag;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
