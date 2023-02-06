package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 求助对象 ma_help
 *
 * @author ruoyi
 * @date 2023-01-20
 */
public class MaHelp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    private Long hid;

    /**
     * 求助者姓名
     */
    @Excel(name = "求助者用户名")
    private String husername;
    /**
     * 求助者姓名
     */
    @Excel(name = "求助者姓名")
    private String hname;

    /**
     * 求助者电话
     */
    @Excel(name = "求助者电话")
    private String hphone;

    /**
     * 求助者地址
     */
    @Excel(name = "求助者地址")
    private String haddress;

    /**
     * 灾害类型
     */
    @Excel(name = "灾害类型")
    private Long disastype;

    /**
     * 求助类型
     */
    @Excel(name = "求助类型")
    private Long needtype;

    /**
     * 申请状态
     */
    @Excel(name = "申请状态")
    private String status;

    /**
     * 提供帮助者姓名
     */
    @Excel(name = "提供帮助者用户名")
    private String hedusername;

    /**
     * 提供帮助者姓名
     */
    @Excel(name = "提供帮助者姓名")
    private String hedname;

    /**
     * 提供帮助者电话
     */
    @Excel(name = "提供帮助者电话")
    private String headphone;

    /**
     * 帮助状态
     */
    @Excel(name = "帮助状态")
    private String hedstatus;

    /**
     * 详细信息
     */
    @Excel(name = "详细信息")
    private String heddetail;

    /**
     * 提供帮助者地址
     */
    @Excel(name = "提供帮助者地址")
    private String hedaddress;

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getHid() {
        return hid;
    }

    public String getHusername() {
        return husername;
    }

    public void setHusername(String husername) {
        this.husername = husername;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHname() {
        return hname;
    }

    public void setHphone(String hphone) {
        this.hphone = hphone;
    }

    public String getHphone() {
        return hphone;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    public String getHaddress() {
        return haddress;
    }

    public void setDisastype(Long disastype) {
        this.disastype = disastype;
    }

    public Long getDisastype() {
        return disastype;
    }

    public void setNeedtype(Long needtype) {
        this.needtype = needtype;
    }

    public Long getNeedtype() {
        return needtype;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setHedname(String hedname) {
        this.hedname = hedname;
    }

    public String getHedname() {
        return hedname;
    }

    public void setHeadphone(String headphone) {
        this.headphone = headphone;
    }

    public String getHeadphone() {
        return headphone;
    }

    public void setHedstatus(String hedstatus) {
        this.hedstatus = hedstatus;
    }

    public String getHedstatus() {
        return hedstatus;
    }

    public void setHeddetail(String heddetail) {
        this.heddetail = heddetail;
    }

    public String getHeddetail() {
        return heddetail;
    }

    public void setHedaddress(String hedaddress) {
        this.hedaddress = hedaddress;
    }

    public String getHedaddress() {
        return hedaddress;
    }

    public String getHedusername() {
        return hedusername;
    }

    public void setHedusername(String hedusername) {
        this.hedusername = hedusername;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("hid", getHid())
                .append("husername", getHusername())
                .append("hname", getHname())
                .append("hphone", getHphone())
                .append("haddress", getHaddress())
                .append("disastype", getDisastype())
                .append("needtype", getNeedtype())
                .append("status", getStatus())
                .append("hedusername",getHedusername())
                .append("hedname", getHedname())
                .append("headphone", getHeadphone())
                .append("hedstatus", getHedstatus())
                .append("heddetail", getHeddetail())
                .append("hedaddress", getHedaddress())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
