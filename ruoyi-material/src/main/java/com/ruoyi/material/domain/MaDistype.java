package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 债还类型对象 ma_distype
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
public class MaDistype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 灾害id */
    private Long disid;

    /** 灾害类型 */
    @Excel(name = "灾害类型")
    private String disname;

    public void setDisid(Long disid) 
    {
        this.disid = disid;
    }

    public Long getDisid() 
    {
        return disid;
    }
    public void setDisname(String disname) 
    {
        this.disname = disname;
    }

    public String getDisname() 
    {
        return disname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("disid", getDisid())
            .append("disname", getDisname())
            .toString();
    }
}
