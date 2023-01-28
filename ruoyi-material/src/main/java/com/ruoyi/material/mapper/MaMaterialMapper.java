package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaMaterial;

/**
 * 物资记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
public interface MaMaterialMapper 
{
    /**
     * 查询物资记录
     * 
     * @param mid 物资记录主键
     * @return 物资记录
     */
    public MaMaterial selectMaMaterialByMid(Long mid);

    /**
     * 查询物资记录列表
     * 
     * @param maMaterial 物资记录
     * @return 物资记录集合
     */
    public List<MaMaterial> selectMaMaterialList(MaMaterial maMaterial);

    /**
     * 新增物资记录
     * 
     * @param maMaterial 物资记录
     * @return 结果
     */
    public int insertMaMaterial(MaMaterial maMaterial);

    /**
     * 修改物资记录
     * 
     * @param maMaterial 物资记录
     * @return 结果
     */
    public int updateMaMaterial(MaMaterial maMaterial);

    /**
     * 删除物资记录
     * 
     * @param mid 物资记录主键
     * @return 结果
     */
    public int deleteMaMaterialByMid(Long mid);

    /**
     * 批量删除物资记录
     * 
     * @param mids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaMaterialByMids(Long[] mids);
}
