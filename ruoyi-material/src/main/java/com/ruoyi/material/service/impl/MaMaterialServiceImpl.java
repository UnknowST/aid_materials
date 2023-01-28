package com.ruoyi.material.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaMaterialMapper;
import com.ruoyi.material.domain.MaMaterial;
import com.ruoyi.material.service.IMaMaterialService;

/**
 * 物资记录Service业务层处理
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
     * 查询物资记录
     * 
     * @param mid 物资记录主键
     * @return 物资记录
     */
    @Override
    public MaMaterial selectMaMaterialByMid(Long mid)
    {
        return maMaterialMapper.selectMaMaterialByMid(mid);
    }

    /**
     * 查询物资记录列表
     * 
     * @param maMaterial 物资记录
     * @return 物资记录
     */
    @Override
    public List<MaMaterial> selectMaMaterialList(MaMaterial maMaterial)
    {
        return maMaterialMapper.selectMaMaterialList(maMaterial);
    }

    /**
     * 新增物资记录
     * 
     * @param maMaterial 物资记录
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
     * 修改物资记录
     * 
     * @param maMaterial 物资记录
     * @return 结果
     */
    @Override
    public int updateMaMaterial(MaMaterial maMaterial)
    {
        maMaterial.setUpdateTime(DateUtils.getNowDate());
        return maMaterialMapper.updateMaMaterial(maMaterial);
    }

    /**
     * 批量删除物资记录
     * 
     * @param mids 需要删除的物资记录主键
     * @return 结果
     */
    @Override
    public int deleteMaMaterialByMids(Long[] mids)
    {
        return maMaterialMapper.deleteMaMaterialByMids(mids);
    }

    /**
     * 删除物资记录信息
     * 
     * @param mid 物资记录主键
     * @return 结果
     */
    @Override
    public int deleteMaMaterialByMid(Long mid)
    {
        return maMaterialMapper.deleteMaMaterialByMid(mid);
    }
}
