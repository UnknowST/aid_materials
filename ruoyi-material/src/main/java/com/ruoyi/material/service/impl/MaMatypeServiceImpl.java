package com.ruoyi.material.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaMatypeMapper;
import com.ruoyi.material.domain.MaMatype;
import com.ruoyi.material.service.IMaMatypeService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
@Service
public class MaMatypeServiceImpl implements IMaMatypeService 
{
    @Autowired
    private MaMatypeMapper maMatypeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param maid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public MaMatype selectMaMatypeByMaid(Long maid)
    {
        return maMatypeMapper.selectMaMatypeByMaid(maid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maMatype 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MaMatype> selectMaMatypeList(MaMatype maMatype)
    {
        return maMatypeMapper.selectMaMatypeList(maMatype);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param maMatype 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMaMatype(MaMatype maMatype)
    {
        return maMatypeMapper.insertMaMatype(maMatype);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param maMatype 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMaMatype(MaMatype maMatype)
    {
        return maMatypeMapper.updateMaMatype(maMatype);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param maids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMaMatypeByMaids(Long[] maids)
    {
        return maMatypeMapper.deleteMaMatypeByMaids(maids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param maid 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMaMatypeByMaid(Long maid)
    {
        return maMatypeMapper.deleteMaMatypeByMaid(maid);
    }
}
