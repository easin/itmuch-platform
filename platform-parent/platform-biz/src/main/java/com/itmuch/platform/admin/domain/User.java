package com.itmuch.platform.admin.domain;

import java.util.Date;

import com.itmuch.core.entity.BaseEntity;

public class User extends BaseEntity {
    /**
     * 编号.
     */
    private Integer id;

    /**
     * 归属公司.
     */
    private Integer companyId;

    /**
     * 归属部门.
     */
    private Integer officeId;

    /**
     * 登录名.
     */
    private String username;

    /**
     * 密码.
     */
    private String password;

    /**
     * 盐.
     */
    private String salt;

    /**
     * 姓名.
     */
    private String name;

    /**
     * 邮箱.
     */
    private String email;

    /**
     * 电话.
     */
    private String phone;

    /**
     * 手机.
     */
    private String mobile;

    /**
     * 用户类型 0普通用户 1管理员.
     */
    private Byte userType;

    /**
     * 用户头像.
     */
    private String photo;

    /**
     * 最后登陆IP.
     */
    private String lastIp;

    /**
     * 最后登陆时间.
     */
    private Date lastTime;

    /**
     * 用户状态 0:正常 1:查封 2:待审核.
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

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getOfficeId() {
        return this.officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Byte getUserType() {
        return this.userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLastIp() {
        return this.lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
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