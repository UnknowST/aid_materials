package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.MaHelped;

/**
 * 帮助记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-27
 */
public interface MaHelpedMapper 
{
    /**
     * 查询帮助记录
     * 
     * @param huid 帮助记录主键
     * @return 帮助记录
     */
    public MaHelped selectMaHelpedByHuid(Long huid);

    /**
     * 查询帮助记录列表
     * 
     * @param maHelped 帮助记录
     * @return 帮助记录集合
     */
    public List<MaHelped> selectMaHelpedList(MaHelped maHelped);

    /**
     * 新增帮助记录
     * 
     * @param maHelped 帮助记录
     * @return 结果
     */
    public int insertMaHelped(MaHelped maHelped);

    /**
     * 修改帮助记录
     * 
     * @param maHelped 帮助记录
     * @return 结果
     */
    public int updateMaHelped(MaHelped maHelped);

    /**
     * 删除帮助记录
     * 
     * @param huid 帮助记录主键
     * @return 结果
     */
    public int deleteMaHelpedByHuid(Long huid);

    /**
     * 批量删除帮助记录
     * 
     * @param huids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaHelpedByHuids(Long[] huids);
}
