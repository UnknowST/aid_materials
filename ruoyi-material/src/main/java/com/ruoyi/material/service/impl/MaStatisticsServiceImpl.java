package com.ruoyi.material.service.impl;

import com.ruoyi.material.mapper.MaStatisticsMapper;
import com.ruoyi.material.service.IMaStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 数值统计Service业务层处理
 *
 * @author ruoyi
 * @date 2023-01-28
 */
@Service
public class MaStatisticsServiceImpl implements IMaStatisticsService {
    @Autowired
    private MaStatisticsMapper statisticsMapper;
    @Override
    public Map<String, Integer> getMaStatistics() {
        Map<String,Integer> map=new HashMap<>();
        map.put("materialsum",statisticsMapper.getMaterialNum());
        map.put("materialing",statisticsMapper.getMaterialNuming());
        map.put("disastypesum",statisticsMapper.getDisastypeSum());
        map.put("needtype",statisticsMapper.getMaterialTypeSum());
        return map;
    }

    @Override
    public Map<String, Integer> getHelpNum() {
        Map<String,Integer> map=new HashMap<>();
        map.put("materialed",statisticsMapper.getGetMaterialNum());
        map.put("materialing",statisticsMapper.getIngMaterialNum());
        map.put("helpnum",statisticsMapper.getHelpNum());
        map.put("helpednum",statisticsMapper.getHelpedNum());
        return map;
    }
}
