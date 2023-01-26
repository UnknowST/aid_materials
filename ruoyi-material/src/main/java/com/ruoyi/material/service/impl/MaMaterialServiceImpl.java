package com.ruoyi.material.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaMaterialMapper;
import com.ruoyi.material.domain.MaMaterial;
import com.ruoyi.material.service.IMaMaterialService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-24
 */
@Service
public class MaMaterialServiceImpl implements IMaMaterialService 
{
    @Autowired
    private MaMaterialMapper maMaterialMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param mid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public MaMaterial selectMaMaterialByMid(Long mid)
    {
        return maMaterialMapper.selectMaMaterialByMid(mid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maMaterial 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MaMaterial> selectMaMaterialList(MaMaterial maMaterial)
    {
        return maMaterialMapper.selectMaMaterialList(maMaterial);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param maMaterial 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMaMaterial(MaMaterial maMaterial)
    {
        maMaterial.setCreateTime(DateUtils.getNowDate());
        int flag=maMaterialMapper.insertMaMaterial(maMaterial);
        if(flag!=0) {
            return Integer.parseInt(maMaterial.getMid().toString());
        }
        return 0;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param maMaterial 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMaMaterial(MaMaterial maMaterial)
    {
        maMaterial.setUpdateTime(DateUtils.getNowDate());
        return maMaterialMapper.updateMaMaterial(maMaterial);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param mids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMaMaterialByMids(Long[] mids)
    {
        return maMaterialMapper.deleteMaMaterialByMids(mids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param mid 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMaMaterialByMid(Long mid)
    {
        return maMaterialMapper.deleteMaMaterialByMid(mid);
    }
}
