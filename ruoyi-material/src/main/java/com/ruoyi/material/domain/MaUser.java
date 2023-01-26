package com.ruoyi.material.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ma_user
 * 
 * @author ruoyi
 * @date 2023-01-17
 */
public class MaUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账号ID */
    private Long userid;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 账号类型 */
    @Excel(name = "账号类型")
    private String usertype;

    /** 性别 0男 1女 2未知 */
    @Excel(name = "性别 0男 1女 2未知")
    private String sex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 用户地址 */
    @Excel(name = "用户地址")
    private String address;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logindata;

    /** 账号状态 0正常 1停用 */
    @Excel(name = "账号状态 0正常 1停用")
    private String status;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 删除标志 0正常 1删除 */
    private String delFlag;

    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setUsertype(String usertype) 
    {
        this.usertype = usertype;
    }

    public String getUsertype() 
    {
        return usertype;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setLogindata(Date logindata) 
    {
        this.logindata = logindata;
    }

    public Date getLogindata() 
    {
        return logindata;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userid", getUserid())
            .append("username", getUsername())
            .append("name", getName())
            .append("password", getPassword())
            .append("usertype", getUsertype())
            .append("sex", getSex())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("logindata", getLogindata())
            .append("status", getStatus())
            .append("avatar", getAvatar())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
