package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaImg;

/**
 * 物资图片Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
public interface MaImgMapper 
{
    /**
     * 查询物资图片
     * 
     * @param imgid 物资图片主键
     * @return 物资图片
     */
    public MaImg selectMaImgByImgid(Long imgid);

    /**
     * 查询物资图片列表
     * 
     * @param maImg 物资图片
     * @return 物资图片集合
     */
    public List<MaImg> selectMaImgList(MaImg maImg);

    /**
     * 新增物资图片
     * 
     * @param maImg 物资图片
     * @return 结果
     */
    public int insertMaImg(MaImg maImg);

    /**
     * 修改物资图片
     * 
     * @param maImg 物资图片
     * @return 结果
     */
    public int updateMaImg(MaImg maImg);

    /**
     * 删除物资图片
     * 
     * @param imgid 物资图片主键
     * @return 结果
     */
    public int deleteMaImgByImgid(Long imgid);

    /**
     * 批量删除物资图片
     * 
     * @param imgids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaImgByImgids(Long[] imgids);
}
