package com.ruoyi.material.service;

import com.ruoyi.material.domain.MaHelp;
import com.ruoyi.material.domain.MaHelped;
import com.ruoyi.material.domain.MaMaterial;

import java.util.List;
import java.util.Map;

/**
 * 数值统计Service接口
 *
 * @author ruoyi
 * @date 2023-01-28
 */
public interface IMaStatisticsService {
    /**
     * 返回统计数据
     * @return
     */
    public Map<String,Integer> getMaStatistics();

    /**
     * 返回帮助统计数据
     * @return
     */
    public Map<String,Integer> getHelpNum();
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
     * 获取不同类型
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
