<template>
  <div class="index">
    <dv-loading v-show="loading">Loading...</dv-loading>
    <div class="body">
      <!-- 头部 -->

      <el-row>
        <el-col :span="6"
          ><dv-decoration-8 class="title_right" :color="['#008CFF', '#00ADDD']"
        /></el-col>
        <el-col :span="12"
          ><div class="title_text">灾 区 物 资 互 助 平 台</div>
          <dv-decoration-5 class="title_center" :color="['#008CFF', '#00ADDD']"
        /></el-col>
        <el-col :span="6"
          ><div class="title_time">{{ Year }}-{{ Mother }}-{{ Day }}</div>
          <dv-decoration-8
            :reverse="true"
            class="title_left"
            :color="['#008CFF', '#00ADDD']"
        /></el-col>
      </el-row>
      <!-- 左边两行 -->

      <el-row>
        <!-- 第一列 -->

        <el-col :span="6">
          <!-- 第一个统计图 -->
          <div class="left_box1">
            <dv-border-box-8 style="width: 100%; height: 100%"
              ><div
                id="helpoption"
                style="height: 100%; width: 100%; padding: 5px"
              ></div
            ></dv-border-box-8>
          </div>
          <!-- 第二个统计图 -->
          <div class="left_box1">
            <dv-border-box-8 style="width: 100%; height: 100%"
              ><div
                id="distypeoption"
                style="height: 100%; width: 100%; padding: 5px"
              ></div>
            </dv-border-box-8>
          </div>
        </el-col>
        <!-- 第二列 -->
        <el-col :span="12">
          <!-- 全国地图 -->
          <div class="left_box2">
            <dv-border-box-12
              ><div
                style="height: 100%; width: 100%; padding: 10px"
                id="map"
              ></div
            ></dv-border-box-12>
            <!-- <dv-decoration-12 style="width: 100%; height: 100%" >
             <dv-charts :option="option" />
        </dv-decoration-12> -->
          </div>
        </el-col>
        <!-- 第三列 -->
        <el-col :span="6">
          <div class="left_box1">
            <!-- 第一个统计图 -->
            <dv-border-box-8 style="width: 100%; height: 100%">
              <div
                style="height: 100%; width: 100%; padding: 5px"
                id="upmaoption"
              ></div>
            </dv-border-box-8>
          </div>
          <!-- 第二个统计图 -->
          <div class="left_box1">
            <dv-border-box-8 style="width: 100%; height: 100%">
              <div
                style="height: 100%; width: 100%; padding: 5px"
                id="needoption"
              ></div>
            </dv-border-box-8>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import {
  getdata,
  helpnumbyday,
  distypenum,
  upmaterialnum,
  getmatypenum,
  getProdata,
  dealWithData,
} from "@/api/ma/index1";
export default {
  name: "Datashow",
  data() {
    return {
      loading: true,
      option1: {},
      mapoption: {},
      helpoption: {},
      distypeoption: {},
      upmaoption: {},
      needoption: {},
      provincedata: [],
      showinfo: null,
      Year: null,
      Mother: null,
      Day: null,
      prodata: [],
    };
  },
  created() {},
  mounted() {
    this.getmap();
    this.gethelpoption();
    this.getDistype();
    this.getUpmanum();
    this.getNeednum();
    this.getprodata();
    this.getNowData();
    this.loading = false;
  },
  methods: {
    getmap() {
      let that = this;
      getdata().then((res) => {
        getProdata().then((res1) => {
          this.provincedata = res1.data;
          for (var i = 0; i < res1.data.length; i++) {
            var tempdata = {};
            if (res1.data[i]["namne"] == "广西省") {
              tempdata = {
                name: "广西壮族自治区",
                value: res1.data[i]["value1"] + res1.data[i]["value2"],
              };
            } else {
              tempdata = {
                name: res1.data[i]["name"],
                value: res1.data[i]["value1"] + res1.data[i]["value2"],
              };
            }
            this.prodata.push(tempdata);
          }
          const myechart = echarts.init(document.getElementById("map"));
          echarts.registerMap("china", res.data);

          var dataValue = this.dealWithData();
          var data1 = dataValue.splice(0, 6);

          var option = {
            tooltip: {
              trigger: "item",
              confine: true,
              formatter: function (p) {
               // console.log(p);
                var toolTiphtml = null;
                if (p.name == "广西壮族自治区") p.name = "广西省";
                for (var i = 0; i < that.provincedata.length; i++) {
                  if (that.provincedata[i].name == p.name) {
                    toolTiphtml = p.name =
                      that.provincedata[i].name +
                      "<br>" +
                      '<span style="display:inline-block:margin-right:5px:border-radius:50%:width:10px:height:10ox:left:5ox:backeround-color: #1fa4"x</span>' +
                      that.provincedata[i].title1 +
                      ":" +
                      that.provincedata[i].value1 +
                      "<br>" +
                      '<span style-"display:inline-block;margin-right:5px;border-radius:50%:width: 10px;height:1x;left:5px;background-color: #9e87f></span>' +
                      that.provincedata[i].title2 +
                      ":" +
                      that.provincedata[i].value2 +
                      "<br>";
                  }
                }
                return toolTiphtml;
              },
            },
            geo: {
              map: "china",
              itemStyle: {
                borderColor: "rgba(196,223,255,1)",
                borderWidth: 2,
                shadowColor: "rgba(196,223,255,.8)",
                shadowBlur: 20,
                shadowOffsetX: 4,
                shadowOffsetY: 4,
              },
            },
            visualMap: {
              type: "piecewise",
              splitNumber: 3,
              min: 60,
              max: 89,
              align: "left",
              right: 20,
              bottom: 20,
              itemWidth: 60,
              itemHeight: 20,
              itemGap: 5,
              // itemSymbol: 'circle',
              orient: "vertical",
              //区域颜色
              color: [
                "#33FFFF",
                "#33FFCC",
                "#33FF99",
                "#33FF66",
                "#33FF33",
                "#33FF00",
                "#33CCFF",
              ],
              //区域颜色范范围
              pieces: [
                { min: 0, max: 50 },
                { min: 50, max: 100 },
                { min: 100, max: 150 },
                { min: 150, max: 200 },
                { min: 200, max: 250 },
                { min: 250, max: 400 },
                { min: 400, max: 1000 },
              ],
              textStyle: {
                color: "#fff",
                fontSize: 14,
              },
            },
            series: [
              {
                map: "china",
                type: "map",
                label: {
                  normal: {
                    color: "rgba(255,255,255,1)",
                    show: false,
                    color: "#fff",
                    textShadowColor: "rgba(0,0,0,0.5)",
                    textShadowBlur: 4,
                    textShadowOffsetX: 0,
                    textShadowOffsetY: 1,
                    fontWeight: "normal",
                  },
                },

                itemStyle: {
                  borderColor: "rgba(196,223,255,1)",
                  borderWidth: 2,
                  areaColor: {
                    type: "linear",
                    x: 0,
                    y: 0,
                    x2: 0,
                    y2: 1,
                    colorStops: [
                      {
                        offset: 0,
                        color: "rgba(102,172,191,1)",
                      },
                      {
                        offset: 1,
                        color: "rgba(21,118,145,1)",
                      },
                    ],
                    global: false,
                  },
                },
                zlevel: 1,
                data: this.prodata,
              },

              {
                name: "Top 5",
                type: "effectScatter",
                coordinateSystem: "geo",
                data: data1,
                symbolSize: 15,
                tooltip: {
                  show: false,
                },
                encode: {
                  value: 2,
                },
                showEffectOn: "render",
                rippleEffect: {
                  brushType: "stroke",
                  color: "#FF0000",
                  period: 9,
                  scale: 5,
                },
                hoverAnimation: true,
                label: {
                  formatter: "{b}",
                  position: "right",
                  show: true,
                },
                itemStyle: {
                  color: "#FF0000",
                  shadowBlur: 2,
                  shadowColor: "#FF0000",
                },
                zlevel: 1,
              },
            ],
          };

          // 使用刚指定的配置项和数据显示图表。
          myechart.setOption(option);

          //响应式变化
          window.addEventListener("resize", () => myechart.resize(), false);
        });
      });
    },
    dealWithData() {
      var geoCoordMap = {
        北京: [116.46, 39.92],
      };
      var data = [];
      for (var key in geoCoordMap) {
        data.push({ name: key, value: geoCoordMap[key] });
      }
      return data;
    },
    gethelpoption() {
      helpnumbyday().then((res) => {
        const hchart = echarts.init(
          document.getElementById("helpoption"),
          "dark"
        );
        let xdata = [];
        for (var i = 0; i < res.data.length; i++)
          xdata[i] = res.data[i]["TIME"];
        let ydata = [];
        for (var i = 0; i < res.data.length; i++) ydata[i] = res.data[i]["num"];

        this.helpoption = {
          backgroundColor: "dark",
          title: {
            text: "每日寻求帮助的人数",
          },
          tooltip: {
            trigger: "axis",
            formatter: function (params) {
              params = params[0];
              var date = new Date(params.name);
              return (
                date.getDate() +
                "/" +
                (date.getMonth() + 1) +
                "/" +
                date.getFullYear() +
                " : " +
                params.data
              );
            },
            axisPointer: {
              animation: false,
            },
          },
          xAxis: {
            type: "category",
            data: xdata,
          },
          yAxis: {
            minInterval: 1,
            axisLabel: {
              formatter: "{value}",
            },
          },
          series: [
            {
              data: ydata,
              type: "line",
            },
          ],
        };
        hchart.setOption(this.helpoption);
        // 响应式变化
        window.addEventListener("resize", () => hchart.resize(), false);
      });
    },
    getDistype() {
      const myechart = echarts.init(
        document.getElementById("distypeoption"),
        "dark"
      );
      distypenum().then((res) => {
        let data = [];

        for (var i = 0; i < res.data.length; i++) {
          var tempdata = {
            name: res.data[i]["disname"],
            value: res.data[i]["num"],
          };
          data.push(tempdata);
        }

        this.distypeoption = {
          title: {
            text: "不同灾害类型求助数",
          },
          tooltip: {
            trigger: "item",
          },

          series: [
            {
              name: "求助数",
              type: "pie",
              radius: ["40%", "70%"],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: "#fff",
                borderWidth: 2,
              },
              label: {
                show: false,
                position: "center",
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: 40,
                  fontWeight: "bold",
                },
              },
              labelLine: {
                show: false,
              },
              data: data,
            },
          ],
        };
        myechart.setOption(this.distypeoption);
        // 响应式变化
        window.addEventListener("resize", () => myechart.resize(), false);
      });
    },
    getUpmanum() {
      upmaterialnum().then((res) => {
        const hchart = echarts.init(
          document.getElementById("upmaoption"),
          "dark"
        );
        let xdata = [];
        for (var i = 0; i < res.data.length; i++)
          xdata[i] = res.data[i]["TIME"];
        let ydata = [];
        for (var i = 0; i < res.data.length; i++) ydata[i] = res.data[i]["num"];

        this.upmaoption = {
          backgroundColor: "dark",
          title: {
            text: "每日物资上传数",
          },
          tooltip: {
            trigger: "axis",
            formatter: function (params) {
              params = params[0];
              var date = new Date(params.name);
              return (
                date.getDate() +
                "/" +
                (date.getMonth() + 1) +
                "/" +
                date.getFullYear() +
                " : " +
                params.data
              );
            },
            axisPointer: {
              animation: false,
            },
          },
          xAxis: {
            type: "category",
            data: xdata,
          },
          yAxis: {},
          series: [
            {
              data: ydata,
              type: "bar",
              smooth: true,
            },
          ],
        };

        hchart.setOption(this.upmaoption);
        // 响应式变化
        window.addEventListener("resize", () => hchart.resize(), false);
      });
    },
    getNeednum() {
      const myechart = echarts.init(
        document.getElementById("needoption"),
        "dark"
      );
      getmatypenum().then((res) => {
        let xdata = [];
        for (var i = 0; i < res.data.length; i++)
          xdata[i] = res.data[i]["maname"];
        let ydata = [];
        for (var i = 0; i < res.data.length; i++) ydata[i] = res.data[i]["num"];
        this.needoption = {
          title: {
            text: "不同物资求助数",
          },
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow",
            },
          },

          xAxis: {
            data: xdata,
          },
          yAxis: {},
          series: [
            {
              type: "scatter",
              name: "求助数",
              data: ydata,
              symbol: "pin",
            },
          ],
        };
        myechart.setOption(this.needoption);
        // 响应式变化
        window.addEventListener("resize", () => myechart.resize(), false);
      });
    },
    getprodata() {
      getProdata().then((res) => {
        this.provincedata = res.data;
        for (var i = 0; i < res.data.length; i++) {
          var tempdata = {};
          if (res.data[i]["namne"] == "广西省") {
            tempdata = {
              name: "广西壮族自治区",
              value: res.data[i]["value1"] + res.data[i]["value2"],
            };
          } else {
            tempdata = {
              name: res.data[i]["name"],
              value: res.data[i]["value1"] + res.data[i]["value2"],
            };
          }
          this.prodata.push(tempdata);
        }
      });
    },
    getNowData() {
      let constdate = new Date();
      this.Year = constdate.getFullYear();
      this.Mother = constdate.getMonth() + 1;
      this.Day = constdate.getDate();
    },
  },
};
</script>

<style lang="scss" scoped>
.index {
  background-color: black;
}
//顶部右边装饰效果
.title_left {
  width: 100%;
  height: 50px;
}
//顶部左边装饰效果
.title_right {
  width: 100%;
  height: 50px;
  margin-top: 18px;
}
//顶部中间装饰效果
.title_center {
  width: 100%;
  height: 50px;
}
//顶部中间文字数据可视化系统
.title_text {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-top: 14px;
  color: #008cff;
}
//时间日期
.title_time {
  text-align: center;
  font-family: "Times New Roman", Times, serif;
  font-size: large;
  color: white;
}

.el-col {
  border-radius: 4px;
  align-items: stretch;
  margin-bottom: 40px;
}
//左1模块
.left_box1 {
  height: 310px;
  width: 100%;
  margin-bottom: 10px;
  position: relative;
}
.left_box2 {
  height: 620px;
  width: 100%;
  margin-bottom: 10px;
  position: relative;
}
</style>