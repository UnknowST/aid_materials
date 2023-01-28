package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图对象 ma_carouselimg
 * 
 * @author ruoyi
 * @date 2023-01-27
 */
public class MaCarouselimg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String imgname;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String imgpath;

    /** 图片状态 0失效 1正常 */
    @Excel(name = "图片状态 0失效 1正常")
    private String imgstatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setImgstatus(String imgstatus) 
    {
        this.imgstatus = imgstatus;
    }

    public String getImgstatus() 
    {
        return imgstatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imgname", getImgname())
            .append("imgpath", getImgpath())
            .append("imgstatus", getImgstatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
