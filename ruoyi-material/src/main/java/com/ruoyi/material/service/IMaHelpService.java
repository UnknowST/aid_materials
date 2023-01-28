package com.ruoyi.material.service;

import java.util.List;
import com.ruoyi.material.domain.MaHelp;

/**
 * 求助Service接口
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
public interface IMaHelpService 
{
    /**
     * 查询求助
     * 
     * @param hid 求助主键
     * @return 求助
     */
    public MaHelp selectMaHelpByHid(Long hid);

    /**
     * 查询求助列表
     * 
     * @param maHelp 求助
     * @return 求助集合
     */
    public List<MaHelp> selectMaHelpList(MaHelp maHelp);

    /**
     * 新增求助
     * 
     * @param maHelp 求助
     * @return 结果
     */
    public int insertMaHelp(MaHelp maHelp);

    /**
     * 修改求助
     * 
     * @param maHelp 求助
     * @return 结果
     */
    public int updateMaHelp(MaHelp maHelp);

    /**
     * 批量删除求助
     * 
     * @param hids 需要删除的求助主键集合
     * @return 结果
     */
    public int deleteMaHelpByHids(Long[] hids);

    /**
     * 删除求助信息
     * 
     * @param hid 求助主键
     * @return 结果
     */
    public int deleteMaHelpByHid(Long hid);
}
