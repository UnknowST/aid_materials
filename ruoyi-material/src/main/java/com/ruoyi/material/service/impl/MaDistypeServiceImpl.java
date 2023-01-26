package com.ruoyi.material.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaDistypeMapper;
import com.ruoyi.material.domain.MaDistype;
import com.ruoyi.material.service.IMaDistypeService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
@Service
public class MaDistypeServiceImpl implements IMaDistypeService 
{
    @Autowired
    private MaDistypeMapper maDistypeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param disid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public MaDistype selectMaDistypeByDisid(Long disid)
    {
        return maDistypeMapper.selectMaDistypeByDisid(disid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maDistype 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MaDistype> selectMaDistypeList(MaDistype maDistype)
    {
        return maDistypeMapper.selectMaDistypeList(maDistype);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param maDistype 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMaDistype(MaDistype maDistype)
    {
        return maDistypeMapper.insertMaDistype(maDistype);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param maDistype 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMaDistype(MaDistype maDistype)
    {
        return maDistypeMapper.updateMaDistype(maDistype);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param disids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMaDistypeByDisids(Long[] disids)
    {
        return maDistypeMapper.deleteMaDistypeByDisids(disids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param disid 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMaDistypeByDisid(Long disid)
    {
        return maDistypeMapper.deleteMaDistypeByDisid(disid);
    }
}
