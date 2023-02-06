<template>
  <div class="app">
    <h1>数据监控大屏</h1>
    <div id="myChart" :style="{ width: '300px', height: '300px' }"></div>
  </div>
</template>

  
  
  <script>
  import * as echarts from 'echarts';
export default {
  name: "Data",

  data() {
    return {};
  },
  created() {},
  mounted() {
    this.drawLine();
  },
  computed: {},
  onpageshow() {},
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById("myChart"));
      // 绘制图表
      myChart.setOption({
        title: { text: "在Vue中使用echarts" },
        tooltip: {},
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [5, 20, 36, 10, 10, 20],
          },
        ],
      });
    },
  },
};
</script>
  <style lang="scss"  scoped>
</style>
  