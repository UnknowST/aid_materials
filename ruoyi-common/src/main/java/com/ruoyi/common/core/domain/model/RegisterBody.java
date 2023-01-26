package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 用户注册对象
 * 
 * @author ruoyi
 */
public class RegisterBody extends LoginBody
{
    // 除去登录数据 再加上 手机号 和 邮箱   地址可以之后更新
    String phonenumber;
    String email;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
