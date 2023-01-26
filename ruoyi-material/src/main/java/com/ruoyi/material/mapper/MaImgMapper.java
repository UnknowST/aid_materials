package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaImg;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
public interface MaImgMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param imgid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MaImg selectMaImgByImgid(Long imgid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maImg 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MaImg> selectMaImgList(MaImg maImg);

    /**
     * 新增【请填写功能名称】
     * 
     * @param maImg 【请填写功能名称】
     * @return 结果
     */
    public int insertMaImg(MaImg maImg);

    /**
     * 修改【请填写功能名称】
     * 
     * @param maImg 【请填写功能名称】
     * @return 结果
     */
    public int updateMaImg(MaImg maImg);

    /**
     * 删除【请填写功能名称】
     * 
     * @param imgid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMaImgByImgid(Long imgid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param imgids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaImgByImgids(Long[] imgids);
}
