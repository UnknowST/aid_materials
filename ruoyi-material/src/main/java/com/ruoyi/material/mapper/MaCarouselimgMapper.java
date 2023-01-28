package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaCarouselimg;

/**
 * 轮播图Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-27
 */
public interface MaCarouselimgMapper 
{
    /**
     * 查询轮播图
     * 
     * @param id 轮播图主键
     * @return 轮播图
     */
    public MaCarouselimg selectMaCarouselimgById(Long id);

    /**
     * 查询轮播图列表
     * 
     * @param maCarouselimg 轮播图
     * @return 轮播图集合
     */
    public List<MaCarouselimg> selectMaCarouselimgList(MaCarouselimg maCarouselimg);

    /**
     * 新增轮播图
     * 
     * @param maCarouselimg 轮播图
     * @return 结果
     */
    public int insertMaCarouselimg(MaCarouselimg maCarouselimg);

    /**
     * 修改轮播图
     * 
     * @param maCarouselimg 轮播图
     * @return 结果
     */
    public int updateMaCarouselimg(MaCarouselimg maCarouselimg);

    /**
     * 删除轮播图
     * 
     * @param id 轮播图主键
     * @return 结果
     */
    public int deleteMaCarouselimgById(Long id);

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaCarouselimgByIds(Long[] ids);
}
