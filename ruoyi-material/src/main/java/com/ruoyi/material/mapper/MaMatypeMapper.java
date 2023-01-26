package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaMatype;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
public interface MaMatypeMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param maid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MaMatype selectMaMatypeByMaid(Long maid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maMatype 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MaMatype> selectMaMatypeList(MaMatype maMatype);

    /**
     * 新增【请填写功能名称】
     * 
     * @param maMatype 【请填写功能名称】
     * @return 结果
     */
    public int insertMaMatype(MaMatype maMatype);

    /**
     * 修改【请填写功能名称】
     * 
     * @param maMatype 【请填写功能名称】
     * @return 结果
     */
    public int updateMaMatype(MaMatype maMatype);

    /**
     * 删除【请填写功能名称】
     * 
     * @param maid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMaMatypeByMaid(Long maid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param maids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaMatypeByMaids(Long[] maids);
}
