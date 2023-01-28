package com.ruoyi.material.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaHelpMapper;
import com.ruoyi.material.domain.MaHelp;
import com.ruoyi.material.service.IMaHelpService;

/**
 * 求助Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
@Service
public class MaHelpServiceImpl implements IMaHelpService 
{
    @Autowired
    private MaHelpMapper maHelpMapper;

    /**
     * 查询求助
     * 
     * @param hid 求助主键
     * @return 求助
     */
    @Override
    public MaHelp selectMaHelpByHid(Long hid)
    {
        return maHelpMapper.selectMaHelpByHid(hid);
    }

    /**
     * 查询求助列表
     * 
     * @param maHelp 求助
     * @return 求助
     */
    @Override
    public List<MaHelp> selectMaHelpList(MaHelp maHelp)
    {
        return maHelpMapper.selectMaHelpList(maHelp);
    }

    /**
     * 新增求助
     * 
     * @param maHelp 求助
     * @return 结果
     */
    @Override
    public int insertMaHelp(MaHelp maHelp)
    {
        maHelp.setCreateTime(DateUtils.getNowDate());
        return maHelpMapper.insertMaHelp(maHelp);
    }

    /**
     * 修改求助
     * 
     * @param maHelp 求助
     * @return 结果
     */
    @Override
    public int updateMaHelp(MaHelp maHelp)
    {
        maHelp.setUpdateTime(DateUtils.getNowDate());
        return maHelpMapper.updateMaHelp(maHelp);
    }

    /**
     * 批量删除求助
     * 
     * @param hids 需要删除的求助主键
     * @return 结果
     */
    @Override
    public int deleteMaHelpByHids(Long[] hids)
    {
        return maHelpMapper.deleteMaHelpByHids(hids);
    }

    /**
     * 删除求助信息
     * 
     * @param hid 求助主键
     * @return 结果
     */
    @Override
    public int deleteMaHelpByHid(Long hid)
    {
        return maHelpMapper.deleteMaHelpByHid(hid);
    }
}
