package com.ruoyi.material.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaCarouselimgMapper;
import com.ruoyi.material.domain.MaCarouselimg;
import com.ruoyi.material.service.IMaCarouselimgService;

/**
 * 轮播图Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-27
 */
@Service
public class MaCarouselimgServiceImpl implements IMaCarouselimgService 
{
    @Autowired
    private MaCarouselimgMapper maCarouselimgMapper;

    /**
     * 查询轮播图
     * 
     * @param id 轮播图主键
     * @return 轮播图
     */
    @Override
    public MaCarouselimg selectMaCarouselimgById(Long id)
    {
        return maCarouselimgMapper.selectMaCarouselimgById(id);
    }

    /**
     * 查询轮播图列表
     * 
     * @param maCarouselimg 轮播图
     * @return 轮播图
     */
    @Override
    public List<MaCarouselimg> selectMaCarouselimgList(MaCarouselimg maCarouselimg)
    {
        return maCarouselimgMapper.selectMaCarouselimgList(maCarouselimg);
    }

    /**
     * 新增轮播图
     * 
     * @param maCarouselimg 轮播图
     * @return 结果
     */
    @Override
    public int insertMaCarouselimg(MaCarouselimg maCarouselimg)
    {
        maCarouselimg.setCreateTime(DateUtils.getNowDate());
        return maCarouselimgMapper.insertMaCarouselimg(maCarouselimg);
    }

    /**
     * 修改轮播图
     * 
     * @param maCarouselimg 轮播图
     * @return 结果
     */
    @Override
    public int updateMaCarouselimg(MaCarouselimg maCarouselimg)
    {
        maCarouselimg.setUpdateTime(DateUtils.getNowDate());
        return maCarouselimgMapper.updateMaCarouselimg(maCarouselimg);
    }

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的轮播图主键
     * @return 结果
     */
    @Override
    public int deleteMaCarouselimgByIds(Long[] ids)
    {
        return maCarouselimgMapper.deleteMaCarouselimgByIds(ids);
    }

    /**
     * 删除轮播图信息
     * 
     * @param id 轮播图主键
     * @return 结果
     */
    @Override
    public int deleteMaCarouselimgById(Long id)
    {
        return maCarouselimgMapper.deleteMaCarouselimgById(id);
    }
}
