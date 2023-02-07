package com.ruoyi.material.mapper;

import com.ruoyi.material.domain.MaHelp;
import com.ruoyi.material.domain.MaMaterial;

import java.util.List;
import java.util.Map;

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

    /** 获取每天求助人数
     * @return
     */
    public List<Map<String,Integer>> getHelpBuDay();

    /**
     * 每日上传物资的人数
     * @return
     */
    public List<Map<String,Integer>> getUpMaterialNum();

    /**
     * 获取不同类型物资的数量
     * @return
     */
    public List<Map<String,Integer>> getMatypeNum();

    /**
     * 获取不同灾害类型的求助数
     * @return
     */
    public List<Map<String,Integer>> getDistypeNum();

    /**
     * 查询每个省份的物资数
     * @return
     */
    public List<MaMaterial> getProMa();

    /**
     * 查询每个省份的求助数
     * @return
     */
    public List<MaHelp> getproHelp();

}