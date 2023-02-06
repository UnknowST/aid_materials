package com.ruoyi.material.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.material.domain.MaNews;
import com.ruoyi.material.service.IMaStatisticsService;
import com.ruoyi.material.service.impl.MaStatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数值统计Controller
 *
 * @author ruoyi
 * @date 2023-01-28
 */
@RestController
@RequestMapping("/ma/statistics")
public class MaStatisticsController extends BaseController {
    @Autowired
    private IMaStatisticsService statisticsService;

    /**
     * 返回物资数值统计数据
     */
    //@PreAuthorize("@ss.hasPermi('ma:statistics:getmanum')")
    @GetMapping("/getmanum")
    public AjaxResult list() {

        return success(statisticsService.getMaStatistics());
    }
    /**
     * 返回帮助数值统计数据
     */
    //@PreAuthorize("@ss.hasPermi('ma:statistics:gethelpnum')")
    @GetMapping("/gethelpnum")
    public AjaxResult helplist() {

        return success(statisticsService.getHelpNum());
    }


}
