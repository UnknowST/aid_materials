package com.ruoyi.material.service;

import com.ruoyi.material.domain.MaHelped;

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
}
