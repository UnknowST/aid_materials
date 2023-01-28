package com.ruoyi.material.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaMatypeMapper;
import com.ruoyi.material.domain.MaMatype;
import com.ruoyi.material.service.IMaMatypeService;

/**
 * 求助类型Service业务层处理
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
     * 查询求助类型
     * 
     * @param maid 求助类型主键
     * @return 求助类型
     */
    @Override
    public MaMatype selectMaMatypeByMaid(Long maid)
    {
        return maMatypeMapper.selectMaMatypeByMaid(maid);
    }

    /**
     * 查询求助类型列表
     * 
     * @param maMatype 求助类型
     * @return 求助类型
     */
    @Override
    public List<MaMatype> selectMaMatypeList(MaMatype maMatype)
    {
        return maMatypeMapper.selectMaMatypeList(maMatype);
    }

    /**
     * 新增求助类型
     * 
     * @param maMatype 求助类型
     * @return 结果
     */
    @Override
    public int insertMaMatype(MaMatype maMatype)
    {
        return maMatypeMapper.insertMaMatype(maMatype);
    }

    /**
     * 修改求助类型
     * 
     * @param maMatype 求助类型
     * @return 结果
     */
    @Override
    public int updateMaMatype(MaMatype maMatype)
    {
        return maMatypeMapper.updateMaMatype(maMatype);
    }

    /**
     * 批量删除求助类型
     * 
     * @param maids 需要删除的求助类型主键
     * @return 结果
     */
    @Override
    public int deleteMaMatypeByMaids(Long[] maids)
    {
        return maMatypeMapper.deleteMaMatypeByMaids(maids);
    }

    /**
     * 删除求助类型信息
     * 
     * @param maid 求助类型主键
     * @return 结果
     */
    @Override
    public int deleteMaMatypeByMaid(Long maid)
    {
        return maMatypeMapper.deleteMaMatypeByMaid(maid);
    }
}
