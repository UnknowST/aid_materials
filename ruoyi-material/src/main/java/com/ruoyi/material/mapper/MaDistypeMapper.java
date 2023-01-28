package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaDistype;

/**
 * 灾害类型Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
public interface MaDistypeMapper 
{
    /**
     * 查询灾害类型
     * 
     * @param disid 灾害类型主键
     * @return 灾害类型
     */
    public MaDistype selectMaDistypeByDisid(Long disid);

    /**
     * 查询灾害类型列表
     * 
     * @param maDistype 灾害类型
     * @return 灾害类型集合
     */
    public List<MaDistype> selectMaDistypeList(MaDistype maDistype);

    /**
     * 新增灾害类型
     * 
     * @param maDistype 灾害类型
     * @return 结果
     */
    public int insertMaDistype(MaDistype maDistype);

    /**
     * 修改灾害类型
     * 
     * @param maDistype 灾害类型
     * @return 结果
     */
    public int updateMaDistype(MaDistype maDistype);

    /**
     * 删除灾害类型
     * 
     * @param disid 灾害类型主键
     * @return 结果
     */
    public int deleteMaDistypeByDisid(Long disid);

    /**
     * 批量删除灾害类型
     * 
     * @param disids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaDistypeByDisids(Long[] disids);
}
