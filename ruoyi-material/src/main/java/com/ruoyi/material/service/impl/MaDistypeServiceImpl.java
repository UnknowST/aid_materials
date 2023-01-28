package com.ruoyi.material.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaDistypeMapper;
import com.ruoyi.material.domain.MaDistype;
import com.ruoyi.material.service.IMaDistypeService;

/**
 * 灾害类型Service业务层处理
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
     * 查询灾害类型
     * 
     * @param disid 灾害类型主键
     * @return 灾害类型
     */
    @Override
    public MaDistype selectMaDistypeByDisid(Long disid)
    {
        return maDistypeMapper.selectMaDistypeByDisid(disid);
    }

    /**
     * 查询灾害类型列表
     * 
     * @param maDistype 灾害类型
     * @return 灾害类型
     */
    @Override
    public List<MaDistype> selectMaDistypeList(MaDistype maDistype)
    {
        return maDistypeMapper.selectMaDistypeList(maDistype);
    }

    /**
     * 新增灾害类型
     * 
     * @param maDistype 灾害类型
     * @return 结果
     */
    @Override
    public int insertMaDistype(MaDistype maDistype)
    {
        return maDistypeMapper.insertMaDistype(maDistype);
    }

    /**
     * 修改灾害类型
     * 
     * @param maDistype 灾害类型
     * @return 结果
     */
    @Override
    public int updateMaDistype(MaDistype maDistype)
    {
        return maDistypeMapper.updateMaDistype(maDistype);
    }

    /**
     * 批量删除灾害类型
     * 
     * @param disids 需要删除的灾害类型主键
     * @return 结果
     */
    @Override
    public int deleteMaDistypeByDisids(Long[] disids)
    {
        return maDistypeMapper.deleteMaDistypeByDisids(disids);
    }

    /**
     * 删除灾害类型信息
     * 
     * @param disid 灾害类型主键
     * @return 结果
     */
    @Override
    public int deleteMaDistypeByDisid(Long disid)
    {
        return maDistypeMapper.deleteMaDistypeByDisid(disid);
    }
}
