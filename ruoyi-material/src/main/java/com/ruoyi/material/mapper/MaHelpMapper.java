package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaHelp;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
public interface MaHelpMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param hid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MaHelp selectMaHelpByHid(Long hid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maHelp 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MaHelp> selectMaHelpList(MaHelp maHelp);

    /**
     * 新增【请填写功能名称】
     * 
     * @param maHelp 【请填写功能名称】
     * @return 结果
     */
    public int insertMaHelp(MaHelp maHelp);

    /**
     * 修改【请填写功能名称】
     * 
     * @param maHelp 【请填写功能名称】
     * @return 结果
     */
    public int updateMaHelp(MaHelp maHelp);

    /**
     * 删除【请填写功能名称】
     * 
     * @param hid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMaHelpByHid(Long hid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param hids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaHelpByHids(Long[] hids);
}
