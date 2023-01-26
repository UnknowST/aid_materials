package com.ruoyi.material.service;

import java.util.List;
import com.ruoyi.material.domain.MaDistype;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
public interface IMaDistypeService 
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
     * 批量删除【请填写功能名称】
     * 
     * @param disids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteMaDistypeByDisids(Long[] disids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param disid 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMaDistypeByDisid(Long disid);
}
