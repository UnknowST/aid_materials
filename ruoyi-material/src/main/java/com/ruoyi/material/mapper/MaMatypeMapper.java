package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaMatype;

/**
 * 求助类型Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-19
 */
public interface MaMatypeMapper 
{
    /**
     * 查询求助类型
     * 
     * @param maid 求助类型主键
     * @return 求助类型
     */
    public MaMatype selectMaMatypeByMaid(Long maid);

    /**
     * 查询求助类型列表
     * 
     * @param maMatype 求助类型
     * @return 求助类型集合
     */
    public List<MaMatype> selectMaMatypeList(MaMatype maMatype);

    /**
     * 新增求助类型
     * 
     * @param maMatype 求助类型
     * @return 结果
     */
    public int insertMaMatype(MaMatype maMatype);

    /**
     * 修改求助类型
     * 
     * @param maMatype 求助类型
     * @return 结果
     */
    public int updateMaMatype(MaMatype maMatype);

    /**
     * 删除求助类型
     * 
     * @param maid 求助类型主键
     * @return 结果
     */
    public int deleteMaMatypeByMaid(Long maid);

    /**
     * 批量删除求助类型
     * 
     * @param maids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaMatypeByMaids(Long[] maids);
}
