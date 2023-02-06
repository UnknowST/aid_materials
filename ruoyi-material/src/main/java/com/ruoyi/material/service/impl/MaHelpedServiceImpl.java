package com.ruoyi.material.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaHelpedMapper;
import com.ruoyi.material.domain.MaHelped;
import com.ruoyi.material.service.IMaHelpedService;

/**
 * 帮助记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-27
 */
@Service
public class MaHelpedServiceImpl implements IMaHelpedService 
{
    @Autowired
    private MaHelpedMapper maHelpedMapper;

    /**
     * 查询帮助记录
     * 
     * @param huid 帮助记录主键
     * @return 帮助记录
     */
    @Override
    public MaHelped selectMaHelpedByHuid(Long huid)
    {
        return maHelpedMapper.selectMaHelpedByHuid(huid);
    }

    /**
     * 查询帮助记录列表
     * 
     * @param maHelped 帮助记录
     * @return 帮助记录
     */
    @Override
    public List<MaHelped> selectMaHelpedList(MaHelped maHelped)
    {
        return maHelpedMapper.selectMaHelpedList(maHelped);
    }

    /**
     * 新增帮助记录
     * 
     * @param maHelped 帮助记录
     * @return 结果
     */
    @Override
    public int insertMaHelped(MaHelped maHelped)
    {
        maHelped.setCreateTime(DateUtils.getNowDate());
        return maHelpedMapper.insertMaHelped(maHelped);
    }

    /**
     * 修改帮助记录
     * 
     * @param maHelped 帮助记录
     * @return 结果
     */
    @Override
    public int updateMaHelped(MaHelped maHelped)
    {
        maHelped.setUpdateTime(DateUtils.getNowDate());
        return maHelpedMapper.updateMaHelped(maHelped);
    }

    /**
     * 批量删除帮助记录
     * 
     * @param huids 需要删除的帮助记录主键
     * @return 结果
     */
    @Override
    public int deleteMaHelpedByHuids(Long[] huids)
    {
        return maHelpedMapper.deleteMaHelpedByHuids(huids);
    }

    /**
     * 删除帮助记录信息
     * 
     * @param huid 帮助记录主键
     * @return 结果
     */
    @Override
    public int deleteMaHelpedByHuid(Long huid)
    {
        return maHelpedMapper.deleteMaHelpedByHuid(huid);
    }

    @Override
    public List<MaHelped> selectMaHelpedByMid(Long mid) {
        return maHelpedMapper.selectMaHelpedByMid(mid);
    }
}
