package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ma_matype
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
public class MaMatype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型id */
    private Long maid;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String maname;

    public void setMaid(Long maid) 
    {
        this.maid = maid;
    }

    public Long getMaid() 
    {
        return maid;
    }
    public void setManame(String maname) 
    {
        this.maname = maname;
    }

    public String getManame() 
    {
        return maname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("maid", getMaid())
            .append("maname", getManame())
            .toString();
    }
}
