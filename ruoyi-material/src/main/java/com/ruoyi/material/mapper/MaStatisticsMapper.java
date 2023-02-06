package com.ruoyi.material.mapper;

/**
 * 数值统计Mapper接口
 *
 * @author ruoyi
 * @date 2023-01-28
 */
public interface MaStatisticsMapper {
    /**
     * 查询现在又多少物质
     *
     * @return
     */
    public int getMaterialNum();

    /**
     * 查询现在有多少物资可用
     *
     * @return
     */
    public int getMaterialNuming();

    /**
     * 查询现在有多少类型物资
     * @return
     */
    public int getMaterialTypeSum();

    /**
     * 查询现在可以为多少种灾害提供帮助
     * @return
     */
    public int getDisastypeSum();

    /**
     * 目前成功申请物资帮助的人数
     * @return
     */
    public int getGetMaterialNum();

    /**
     * 目前正在申请物资的人数
     * @return
     */
    public int getIngMaterialNum();

    /**
     * 目前申请帮助的人数
     * @return
     */
    public int getHelpNum();

    /**
     * 目前成功申请当帮助的人数
     * @return
     */
    public int getHelpedNum();

}