package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ma_material
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
public class MaMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long mid;

    /** 物资标题 */
    @Excel(name = "物资标题")
    private String mtitle;

    /** 物资详细信息 */
    @Excel(name = "物资详细信息")
    private String mdetail;

    /** 灾害类型 */
    @Excel(name = "灾害类型")
    private Long mtype;

    /** 图片id */
    @Excel(name = "图片id")
    private Long mimagid;

    /** 地址 */
    @Excel(name = "地址")
    private String maddress;

    /** 物资状态（0待审核 1审核通过 2已使用） */
    @Excel(name = "物资状态", readConverterExp = "0=待审核,1=审核通过,2=已使用")
    private String mstatus;

    /** 删除标志（0正常 1删除） */
    private String delFlag;

    public void setMid(Long mid) 
    {
        this.mid = mid;
    }

    public Long getMid() 
    {
        return mid;
    }
    public void setMtitle(String mtitle) 
    {
        this.mtitle = mtitle;
    }

    public String getMtitle() 
    {
        return mtitle;
    }
    public void setMdetail(String mdetail) 
    {
        this.mdetail = mdetail;
    }

    public String getMdetail() 
    {
        return mdetail;
    }
    public void setMtype(Long mtype) 
    {
        this.mtype = mtype;
    }

    public Long getMtype() 
    {
        return mtype;
    }
    public void setMimagid(Long mimagid) 
    {
        this.mimagid = mimagid;
    }

    public Long getMimagid() 
    {
        return mimagid;
    }
    public void setMaddress(String maddress) 
    {
        this.maddress = maddress;
    }

    public String getMaddress() 
    {
        return maddress;
    }
    public void setMstatus(String mstatus) 
    {
        this.mstatus = mstatus;
    }

    public String getMstatus() 
    {
        return mstatus;
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
            .append("mid", getMid())
            .append("mtitle", getMtitle())
            .append("mdetail", getMdetail())
            .append("mtype", getMtype())
            .append("mimagid", getMimagid())
            .append("maddress", getMaddress())
            .append("mstatus", getMstatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
