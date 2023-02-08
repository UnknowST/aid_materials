package com.ruoyi.material.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
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
import java.util.*;
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
    public AjaxResult helpbyday() {
        return success(statisticsService.getHelpBuDay());
    }

    @GetMapping("/getdistypenum")
    public AjaxResult distypenum() {
        return success(statisticsService.getDistypeNum());
    }

    @GetMapping("/getmatypenum")
    public AjaxResult matypenum() {
        return success(statisticsService.getMatypeNum());
    }

    @GetMapping("/getupmanum")
    public AjaxResult upmaterialnum() {
        return success(statisticsService.getUpMaterialNum());
    }

    @GetMapping("/getprodata")
    public AjaxResult getprodata() {
        class provincedata {
            String name;
            String title1 = "物资数";
            Integer value1=0;
            String title2 = "求助数";
            Integer value2=0;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle1() {
                return title1;
            }

            public void setTitle1(String title1) {
                this.title1 = title1;
            }

            public Integer getValue1() {
                return value1;
            }

            public void setValue1(Integer value1) {
                this.value1 = value1;
            }

            public String getTitle2() {
                return title2;
            }

            public void setTitle2(String title2) {
                this.title2 = title2;
            }

            public Integer getValue2() {
                return value2;
            }

            public void setValue2(Integer value2) {
                this.value2 = value2;
            }

            @Override
            public String toString() {
                return "provincedata{" +
                        "name='" + name + '\'' +
                        ", title1='" + title1 + '\'' +
                        ", value1=" + value1 +
                        ", title2='" + title2 + '\'' +
                        ", value2=" + value2 +
                        '}';
            }
        }
        List<provincedata> prolist = new ArrayList<>();
       String[] provincelist = new String[] {
                "北京市",
                "上海市",
                "天津市",
                "重庆市",
                "河北省",
                "山西省",
                "辽宁省",
                "吉林省",
                "黑龙江省",
                "浙江省",
                "福建省",
                "山东省",
                "河南省",
                "湖北省",
                "湖南省",
                "广东省",
                "海南省",
                "四川省",
                "贵州省",
                "云南省",
                "江西省",
                "陕西省",
                "青海省",
                "甘肃省",
                "广西省",
                "新疆维吾尔自治区",
                "内蒙古自治区",
                "西藏自治区",
                "台湾省",
                "香港特别行政区",
                "澳门行政区",
                "安徽省",
                "江苏省"};
        for(String name:provincelist){
            provincedata pro=new provincedata();
            pro.setName(name);
            prolist.add(pro);
        }

        List<MaMaterial> list = statisticsService.getProMa();
        List<MaHelp> list1 = statisticsService.getproHelp();
//        //先准备一个map的全国身份数据
//        List<HashMap<String, List<Integer>>> list2 = new ArrayList<>();
//        HashMap<String, List<Integer>> map = new HashMap<>();
//        map.put("北京市", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("上海市", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("天津市", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("重庆市", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("河北省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("山西省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("辽宁省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("吉林省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("黑龙江省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("浙江省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("福建省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("山东省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("河南省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("湖北省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("湖南省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("广东省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("海南省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("四川省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("贵州省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("云南省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("江西省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("陕西省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("青海省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("甘肃省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("广西省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("新疆维吾尔自治区", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("内蒙古自治区", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("西藏自治区", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("台湾省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("香港特别行政区", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("澳门行政区", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("安徽省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        map.put("江苏省", new ArrayList<Integer>(Arrays.asList(0, 0)));
//        list2.add(map);
        for (int i = 0; i < list.size(); i++) {
            //Pattern创建正则，matcher验证正则

            System.out.println(list.get(i).getMaddress());

            String province = getProvince(list.get(i).getMaddress());
            System.out.println(province);
            for(provincedata p:prolist){
                if(p.getName().equals(province)) {
                    p.setValue1(p.getValue1()+Integer.valueOf(list.get(i).getMnum()));
                }
            }
            //map.get(province).set(0, map.get(province).get(0) + Integer.valueOf(list.get(i).getMnum()));
        }

        for (int i = 0; i < list1.size(); i++) {
            //Pattern创建正则，matcher验证正则

            System.out.println(list1.get(i).getHaddress());

            String province = getProvince(list1.get(i).getHaddress());
            System.out.println(province);
            for(provincedata p:prolist){
                if(p.getName().equals(province)) {
                    p.setValue2(p.getValue2()+1);
                }
            }
            //map.get(province).set(1, map.get(province).get(1) + 1);
        }

        return success(prolist);
    }

    public String getProvince(String address) {
        if (address.startsWith("北京市") || address.startsWith("天津市") || address.startsWith("上海市") || address.startsWith("重庆市")) {
            address = address.substring(0, 3) + "市辖区" + address.substring(3);
        }
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null, town = null, village = null;
        while (m.find()) {
            province = m.group("province");

            if (province.equals("北京市") || province.equals("天津市") || province.equals("上海市") || province.equals("重庆市")) {
                city = province;

                county = m.group("city");
                if (county.split("区").length > 1) {
                    town = county.substring(county.indexOf("区") + 1);
                    county = county.substring(0, county.indexOf("区") + 1);
                    if (town.contains("区")) {
                        town = town.substring(county.indexOf("区") + 1);
                    }
                } else {
                    county = m.group("county");
                    if (county.split("区").length > 1) {
                        town = county.substring(county.indexOf("区") + 1);
                        county = county.substring(0, county.indexOf("区") + 1);
                    }
                }
            } else {
                city = m.group("city");

                county = m.group("county");
                if (county != null && !"".equals(county)) {
                    if (county.split("市").length > 1 && county.indexOf("市") < 5) {
                        town = county;
                        county = county.substring(0, county.indexOf("市") + 1);
                        town = town.substring(county.indexOf("市") + 1);
                    }
                    if (county.split("旗").length > 1) {
                        town = county;
                        county = county.substring(0, county.indexOf("旗") + 1);
                        town = town.substring(county.indexOf("旗") + 1);
                    }
                    if (county.split("海域").length > 1) {
                        town = county;
                        county = county.substring(0, county.indexOf("海域") + 2);
                        town = town.substring(county.indexOf("海域") + 2);
                    }
                    if (county.split("区").length > 1) {
                        town = county;
                        county = county.substring(0, county.indexOf("区") + 1);
                        town = town.substring(county.indexOf("区") + 1);
                    }
                }

            }


            town += m.group("town");
            if ((county == null || "".equals(county)) && town != null && !"".equals(town)) {
                town = town + "-";
            }
            village = m.group("village");

        }
        return province;
    }
}
