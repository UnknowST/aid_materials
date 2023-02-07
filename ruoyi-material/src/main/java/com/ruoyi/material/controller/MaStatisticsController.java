package com.ruoyi.material.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.material.domain.MaHelp;
import com.ruoyi.material.domain.MaMaterial;
import com.ruoyi.material.domain.MaNews;
import com.ruoyi.material.service.IMaStatisticsService;
import com.ruoyi.material.service.impl.MaStatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @GetMapping("/gethelpByday")
    public AjaxResult helpbyday(){
        return success(statisticsService.getHelpBuDay());
    }
    @GetMapping("/getdistypenum")
    public AjaxResult distypenum(){
        return success(statisticsService.getDistypeNum());
    }
    @GetMapping("/getmatypenum")
    public AjaxResult matypenum(){
        return success(statisticsService.getMatypeNum());
    }
    @GetMapping("/getupmanum")
    public AjaxResult upmaterialnum(){
        return success(statisticsService.getUpMaterialNum());
    }

    @GetMapping("/getprodata")
    public AjaxResult getprodata(){
//        List<MaMaterial> list=statisticsService.getProMa();
//        List<MaHelp> list1=statisticsService.getproHelp();
//        //先准备一个map的全国身份数据
//        HashMap<String, List<Integer, Integer>> map=new HashMap<>();
//        //正则验证表达式
//        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)" +
//                "(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)" +
//                "(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)" +
//                "?(?<town>[^区]+区|.+镇)?" +
//                "(?<village>.*)";
//        //Pattern创建正则，matcher验证正则
//        Matcher m = Pattern.compile(regex).matcher(address);
//
//        map.put("北京市","116.3979471,39.9081726" );
//        map.put("上海市","121.4692688,31.2381763" );
//        map.put("天津市","117.2523808,39.1038561" );
//        map.put("重庆市","106.548425,29.5549144" );
//        map.put("河北省","114.4897766,38.0451279" );
//        map.put("山西省","112.5223053,37.8357424" );
//        map.put("辽宁省","123.4116821,41.7966156" );
//        map.put("吉林省","125.3154297,43.8925629" );
//        map.put("黑龙江省","126.6433411,45.7414932" );
//        map.put("浙江省","120.1592484,30.265995" );
//        map.put("福建省","119.2978134,26.0785904" );
//        map.put("山东省","117.0056,36.6670723" );
//        map.put("河南省","113.6500473,34.7570343" );
//        map.put("湖北省","114.2919388,30.5675144" );
//        map.put("湖南省","112.9812698,28.2008247" );
//        map.put("广东省","113.2614288,23.1189117" );
//        map.put("海南省","110.3465118,20.0317936" );
//        map.put("四川","104.0817566,30.6610565" );
//        map.put("贵州","106.7113724,26.5768738" );
//        map.put("云南","102.704567,25.0438442" );
//        map.put("江西省","115.8999176,28.6759911" );
//        map.put("陕西省","108.949028,34.2616844" );
//        map.put("青海省","101.7874527,36.6094475" );
//        map.put("甘肃省","103.7500534,36.0680389" );
//        map.put("广西省","108.3117676,22.8065434" );
//        map.put("新疆省","87.6061172,43.7909393" );
//        map.put("内蒙古省","111.6632996,40.8209419" );
//        map.put("西藏省","91.1320496,29.657589" );
//        map.put("台湾","120.9581316,23.8516062" );
//        map.put("香港","114.139452,22.391577" );
//        map.put("澳门","113.5678411,22.167654" );
//        map.put("安徽省","117.2757034,31.8632545" );
//        map.put("江苏省省","118.7727814,32.0476151" );


        return success(statisticsService.getUpMaterialNum());
    }


}
