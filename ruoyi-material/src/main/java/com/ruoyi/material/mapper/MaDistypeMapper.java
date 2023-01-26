package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaDistype;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
public interface MaDistypeMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param disid 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MaDistype selectMaDistypeByDisid(Long disid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param maDistype 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MaDistype> selectMaDistypeList(MaDistype maDistype);

    /**
     * 新增【请填写功能名称】
     * 
     * @param maDistype 【请填写功能名称】
     * @return 结果
     */
    public int insertMaDistype(MaDistype maDistype);

    /**
     * 修改【请填写功能名称】
     * 
     * @param maDistype 【请填写功能名称】
     * @return 结果
     */
    public int updateMaDistype(MaDistype maDistype);

    /**
     * 删除【请填写功能名称】
     * 
     * @param disid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMaDistypeByDisid(Long disid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param disids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaDistypeByDisids(Long[] disids);
}
