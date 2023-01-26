package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaMaterial;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
public interface MaMaterialMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param mid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MaMaterial selectMaMaterialByMid(Long mid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maMaterial 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MaMaterial> selectMaMaterialList(MaMaterial maMaterial);

    /**
     * 新增【请填写功能名称】
     * 
     * @param maMaterial 【请填写功能名称】
     * @return 结果
     */
    public int insertMaMaterial(MaMaterial maMaterial);

    /**
     * 修改【请填写功能名称】
     * 
     * @param maMaterial 【请填写功能名称】
     * @return 结果
     */
    public int updateMaMaterial(MaMaterial maMaterial);

    /**
     * 删除【请填写功能名称】
     * 
     * @param mid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMaMaterialByMid(Long mid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param mids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaMaterialByMids(Long[] mids);
}
