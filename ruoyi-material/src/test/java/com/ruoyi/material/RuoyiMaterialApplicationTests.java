package com.ruoyi.material;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class RuoyiMaterialApplicationTests {

    @Test
    void contextLoads() {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)" +
                "(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)" +
                "(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)" +
                "?(?<town>[^区]+区|.+镇)?" +
                "(?<village>.*)";
        //Pattern创建正则，matcher验证正则
        Matcher m = Pattern.compile(regex).matcher("贵州省六盘水市盘州市");
        System.out.println(m);
    }

}
