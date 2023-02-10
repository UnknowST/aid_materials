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
} from "@/api/ma/index1";
export default {
  name: "Datashow",
  data() {
    return {
      loading: true,
      option1: {},
      option: {
        title: {
          text: "剩余油量表",
          style: {
            fill: "#fff",
          },
        },
        series: [
          {
            type: "gauge",
            data: [{ name: "itemA", value: 55 }],
            center: ["50%", "55%"],
            axisLabel: {
              formatter: "{value}%",
              style: {
                fill: "#fff",
              },
            },
            axisTick: {
              style: {
                stroke: "#fff",
              },
            },
            animationCurve: "easeInOutBack",
          },
        ],
      },
      mapoption: {},
      helpoption: {},
      distypeoption: {},
      upmaoption: {},
      needoption: {},
      provincedata: [],
      showinfo: null,
    Year:null,
    Mother:null,
    Day:null,
    };
  },
  created() {},
  mounted() {
    this.test();
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
    test() {
      this.option1 = {
        title: {
          text: "剩余油量表",
          style: {
            fill: "#fff",
          },
        },
        series: [
          {
            type: "gauge",
            data: [{ name: "itemA", value: 55 }],
            center: ["50%", "55%"],
            axisLabel: {
              formatter: "{value}%",
              style: {
                fill: "#fff",
              },
            },
            axisTick: {
              style: {
                stroke: "#fff",
              },
            },
            animationCurve: "easeInOutBack",
          },
        ],
      };
    },
    getmap() {
        let that=this
      getdata().then((res) => {
        
        const myechart = echarts.init(document.getElementById("map"), "dark");
        echarts.registerMap("china", res.data);
        this.mapoption = {
         
          tooltip: {
            trigger: "item",
            confine: true,
            formatter: function (p) {
                var toolTiphtml=null
              if(p.name=="广西壮族自治区") p.name="广西省"
                 for(var i=0;i<that.provincedata.length;i++){
                    if(that.provincedata[i].name==p.name) {
                         toolTiphtml = p.name=that.provincedata[i].name + '<br>'
                +'<span style="display:inline-block:margin-right:5px:border-radius:50%:width:10px:height:10ox:left:5ox:backeround-color: #1fa4"x</span>'
                    + that.provincedata[i].title1 +  ':'+ that.provincedata[i].value1 + '<br>'+
                    '<span style-"display:inline-block;margin-right:5px;border-radius:50%:width: 10px;height:1x;left:5px;background-color: #9e87f></span>'
                     + that.provincedata[i].title2+':'+that.provincedata[i].value2 
                     + '<br>'
                    }
                }
              return toolTiphtml;
            },
          },
          geo: {
            // 作为底图，设置地图外围边框
            map: "china",
            zoom:1.2,
            itemStyle: {
              areaColor: "#fff",
              borderColor: "#333",
              borderWidth: 1,
            },
          },

          series: [
            {
                
              data: this.provincedata,
              type: "map",
              map: "china",
             
              geoIndex:0,
              itemStyle: {
                areaColor: "#fff",
                borderColor: "#333",
                borderType: [2, 4],
                borderDashOffset: 4,
              },
              emphasis: {
                // 鼠标悬停时样式
                label: {
                  color: "rgb(0, 60, 131)",
                },
                itemStyle: {
                  areaColor: "rgba(0, 60, 131, 0.3)",
                  borderType: "solid",
                  borderColor: "rgb(0, 60, 131)",
                },
              },
              select: {
                // 选中时样式
                label: {
                  color: "#fff",
                },
                itemStyle: {
                  areaColor: "rgba(0, 60, 131, 0.7)",
                  borderType: "solid",
                  borderColor: "rgb(0, 60, 131)",
                },
              },
          
            },
            
          ],
        };
        myechart.setOption(this.mapoption);
      });
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
          yAxis: {},
          series: [
            {
              data: ydata,
              type: "line",
              smooth: true,
            },
          ],
        };
        hchart.setOption(this.helpoption);
      });
    },
    getDistype() {
      const myechart = echarts.init(
        document.getElementById("distypeoption"),
        "dark"
      );
      distypenum().then((res) => {
       
        let xdata = [];
        for (var i = 0; i < res.data.length; i++)
          xdata[i] = res.data[i]["disname"];
        let ydata = [];
        for (var i = 0; i < res.data.length; i++) ydata[i] = res.data[i]["num"];
        this.distypeoption = {
          title: {
            text: "不同灾害类型求助数",
          },
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow",
            },
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: [
            {
              type: "category",
              data: xdata,
              axisTick: {
                alignWithLabel: true,
              },
            },
          ],
          yAxis: [
            {
              type: "value",
            },
          ],
          series: [
            {
              name: "Direct",
              type: "bar",
              barWidth: "60%",
              data: ydata,
            },
          ],
        };
        myechart.setOption(this.distypeoption);
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
              type: "line",
              smooth: true,
            },
          ],
        };
        hchart.setOption(this.upmaoption);
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
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: [
            {
              type: "category",
              data: xdata,
              axisTick: {
                alignWithLabel: true,
              },
            },
          ],
          yAxis: [
            {
              type: "value",
            },
          ],
          series: [
            {
              name: "Direct",
              type: "bar",
              barWidth: "60%",
              data: ydata,
            },
          ],
        };
        myechart.setOption(this.needoption);
      });
    },
    getprodata() {
      getProdata().then((res) => {
      

        this.provincedata = res.data;
  
      });
    },
    getNowData(){
       let constdate=new Date();
       this.Year=constdate.getFullYear();
       this.Mother=constdate.getMonth()+1;
       this.Day=constdate.getDate();
      
       
    },

  },
};
</script>

<style lang="scss" scoped>
.index{
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
  font-family: 'Times New Roman', Times, serif;
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