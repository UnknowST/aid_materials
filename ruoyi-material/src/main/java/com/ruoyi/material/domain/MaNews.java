package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻列对象 ma_news
 *
 * @author ruoyi
 * @date 2023-01-28
 */
public class MaNews extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 新闻id
     */
    @Excel(name = "新闻id")
    private Long nid;

    /**
     * 新闻标题
     */
    @Excel(name = "新闻作者")
    private String author;

    /**
     * 新闻标题
     */
    @Excel(name = "新闻标题")
    private String ntitle;

    /**
     * 新闻内容
     */
    @Excel(name = "新闻内容")
    private String ndetail;

    /**
     * 阅读量
     */
    @Excel(name = "阅读量")
    private String nread;

    /**
     * 删除标志
     */
    @Excel(name = "删除标志")
    private String deltype;

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public Long getNid() {
        return nid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNdetail(String ndetail) {
        this.ndetail = ndetail;
    }

    public String getNdetail() {
        return ndetail;
    }

    public void setNread(String nread) {
        this.nread = nread;
    }

    public String getNread() {
        return nread;
    }

    public void setDeltype(String deltype) {
        this.deltype = deltype;
    }

    public String getDeltype() {
        return deltype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("nid", getNid())
                .append("author", getAuthor())
                .append("ntitle", getNtitle())
                .append("ndetail", getNdetail())
                .append("nread", getNread())
                .append("deltype", getDeltype())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
