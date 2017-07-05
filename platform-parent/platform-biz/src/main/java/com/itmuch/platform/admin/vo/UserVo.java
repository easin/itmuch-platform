package com.itmuch.platform.admin.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserVo {
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 部门名称
     */
    private String officeName;

    /**
     * 编号.
     */
    private Integer id;

    /**
     * 归属公司.
     */
    private String companyId;

    /**
     * 归属部门.
     */
    private String officeId;

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

    private List<Integer> roleIds;
    /**
     * 工号.
     */
    private String no;

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
    private String createBy;

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

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOfficeName() {
        return this.officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOfficeId() {
        return this.officeId;
    }

    public void setOfficeId(String officeId) {
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

    public List<Integer> getRoleIds() {
        return this.roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
