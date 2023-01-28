package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物资图片对象 ma_img
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
public class MaImg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片id */
    private Long imgid;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String imgname;



    /** 图片路径 */
    @Excel(name = "图片路径")
    private String imgpath;

    /** 物资id */
    @Excel(name = "物资id")
    private Long mid;

    public void setImgid(Long imgid) 
    {
        this.imgid = imgid;
    }

    public Long getImgid() 
    {
        return imgid;
    }
    public void setImgname(String imgname) 
    {
        this.imgname = imgname;
    }

    public String getImgname()
    {
        return imgname;
    }

    public void setImgpath(String imgpath)
    {
        this.imgpath = imgpath;
    }

    public String getImgpath() 
    {
        return imgpath;
    }
    public void setMid(Long mid) 
    {
        this.mid = mid;
    }

    public Long getMid() 
    {
        return mid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("imgid", getImgid())
            .append("imgname", getImgname())
            .append("imgpath", getImgpath())
            .append("mid", getMid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
