package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 帮助记录对象 ma_helped
 * 
 * @author ruoyi
 * @date 2023-01-27
 */
public class MaHelped extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 求助id */
    @Excel(name = "求助id")
    private Long huid;

    /** 物资id */
    @Excel(name = "物资id")
    private Long mid;

    /** 用户名 */
    @Excel(name = "用户名")
    private String husername;

    /** 姓名 */
    @Excel(name = "姓名")
    private String hname;

    /** 电话 */
    @Excel(name = "电话")
    private String hphone;

    /** 地址 */
    @Excel(name = "地址")
    private String haddress;

    /** 灾害类型 */
    @Excel(name = "灾害类型")
    private Long disastype;

    /** 求助物资类型 */
    @Excel(name = "求助物资类型")
    private Long needtype;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setHuid(Long huid) 
    {
        this.huid = huid;
    }

    public Long getHuid() 
    {
        return huid;
    }
    public void setMid(Long mid) 
    {
        this.mid = mid;
    }

    public Long getMid() 
    {
        return mid;
    }
    public void setHusername(String husername) 
    {
        this.husername = husername;
    }

    public String getHusername() 
    {
        return husername;
    }
    public void setHname(String hname) 
    {
        this.hname = hname;
    }

    public String getHname() 
    {
        return hname;
    }
    public void setHphone(String hphone) 
    {
        this.hphone = hphone;
    }

    public String getHphone() 
    {
        return hphone;
    }
    public void setHaddress(String haddress) 
    {
        this.haddress = haddress;
    }

    public String getHaddress() 
    {
        return haddress;
    }
    public void setdisastype(Long disastype) 
    {
        this.disastype = disastype;
    }

    public Long getdisastype() 
    {
        return disastype;
    }
    public void setNeedtype(Long needtype) 
    {
        this.needtype = needtype;
    }

    public Long getNeedtype() 
    {
        return needtype;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("huid", getHuid())
            .append("mid", getMid())
            .append("husername", getHusername())
            .append("hname", getHname())
            .append("hphone", getHphone())
            .append("haddress", getHaddress())
            .append("disastype", getdisastype())
            .append("needtype", getNeedtype())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
