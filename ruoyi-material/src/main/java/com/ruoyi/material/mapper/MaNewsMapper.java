package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaNews;

/**
 * 新闻列Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-28
 */
public interface MaNewsMapper 
{
    /**
     * 查询新闻列
     * 
     * @param nid 新闻列主键
     * @return 新闻列
     */
    public MaNews selectMaNewsByNid(Long nid);

    /**
     * 查询新闻列列表
     * 
     * @param maNews 新闻列
     * @return 新闻列集合
     */
    public List<MaNews> selectMaNewsList(MaNews maNews);

    /**
     * 新增新闻列
     * 
     * @param maNews 新闻列
     * @return 结果
     */
    public int insertMaNews(MaNews maNews);

    /**
     * 修改新闻列
     * 
     * @param maNews 新闻列
     * @return 结果
     */
    public int updateMaNews(MaNews maNews);

    /**
     * 删除新闻列
     * 
     * @param nid 新闻列主键
     * @return 结果
     */
    public int deleteMaNewsByNid(Long nid);

    /**
     * 批量删除新闻列
     * 
     * @param nids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaNewsByNids(Long[] nids);
}
