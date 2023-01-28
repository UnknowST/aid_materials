package com.ruoyi.material.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaNewsMapper;
import com.ruoyi.material.domain.MaNews;
import com.ruoyi.material.service.IMaNewsService;

/**
 * 新闻列Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-28
 */
@Service
public class MaNewsServiceImpl implements IMaNewsService 
{
    @Autowired
    private MaNewsMapper maNewsMapper;

    /**
     * 查询新闻列
     * 
     * @param nid 新闻列主键
     * @return 新闻列
     */
    @Override
    public MaNews selectMaNewsByNid(Long nid)
    {
        return maNewsMapper.selectMaNewsByNid(nid);
    }

    /**
     * 查询新闻列列表
     * 
     * @param maNews 新闻列
     * @return 新闻列
     */
    @Override
    public List<MaNews> selectMaNewsList(MaNews maNews)
    {
        return maNewsMapper.selectMaNewsList(maNews);
    }

    /**
     * 新增新闻列
     * 
     * @param maNews 新闻列
     * @return 结果
     */
    @Override
    public int insertMaNews(MaNews maNews)
    {
        maNews.setCreateTime(DateUtils.getNowDate());
        return maNewsMapper.insertMaNews(maNews);
    }

    /**
     * 修改新闻列
     * 
     * @param maNews 新闻列
     * @return 结果
     */
    @Override
    public int updateMaNews(MaNews maNews)
    {
        maNews.setUpdateTime(DateUtils.getNowDate());
        return maNewsMapper.updateMaNews(maNews);
    }

    /**
     * 批量删除新闻列
     * 
     * @param nids 需要删除的新闻列主键
     * @return 结果
     */
    @Override
    public int deleteMaNewsByNids(Long[] nids)
    {
        return maNewsMapper.deleteMaNewsByNids(nids);
    }

    /**
     * 删除新闻列信息
     * 
     * @param nid 新闻列主键
     * @return 结果
     */
    @Override
    public int deleteMaNewsByNid(Long nid)
    {
        return maNewsMapper.deleteMaNewsByNid(nid);
    }
}
