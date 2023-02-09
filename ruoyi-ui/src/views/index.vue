<template>
  <div id="app">
    <!--传递不同的currentIndex 作为区分不同swiper组件的动态id-->
    <!-- <CarouselContainer :slide-list="list" currentIndex="1"></CarouselContainer> -->
    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="(item, index) of list" :key="index">
        <img :src="item.imgpath" />
      </el-carousel-item>
    </el-carousel>
    <!-- 分割线 -->
    <!-- <el-divider></el-divider> -->
    <el-card class="box-card" style="height: 192px">
      <div class="title-text">
        <h1>灾区物资互助平台</h1>
      </div>
      <div class="slogan">一方有难，八方支援。</div>
      <div class="login-item">
        <div v-if="islogin">
          <router-link :to="'/index1'">进入系统</router-link>
        </div>
        <div v-if="!islogin">
          <router-link :to="'/login'">登录注册</router-link>
        </div>
        <!-- <a class="link-type" @click="checkLogin" style="font-size:20px;color:cornflowerblue">前往登录</a></div>   -->
      </div>
    </el-card>
    <div>
      <el-row :gutter="20">
        <el-col :span="10" :lg="12" style="padding-left: 20px">
          <h2 style="padding-left: 10px">新闻资讯</h2>
          <el-collapse
            accordion
            v-for="(item, index) of newslist1"
            :key="index"
          >
            <el-collapse-item :title="item.ntitle">
              <ol style="padding-left: 5px">
                <span style="padding-left: 10px">{{ item.author }}</span
                ><span style="padding-left: 20px">{{ item.createTime }}</span>
                <span style="padding-left: 10px">阅读量：{{ item.nread }}</span>
                <span style="padding-left: 10px">
                  <el-button
                    type="warning"
                    icon="el-icon-reading"
                    circle
                    @click="opennews(item.nid, item.nread)"
                  ></el-button>
                </span>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-col>
        <el-col :span="10" :lg="12" style="padding-left: 20px">
          <h2 style="padding-left: 10px">通知公告</h2>
          <el-collapse
            accordion
            v-for="(item, index) of noticelist"
            :key="index"
          >
            <el-collapse-item :title="item.noticeTitle">
              <ol style="padding-left: 5px">
                <div
                  style="padding-left: 10px"
                  v-html="item.noticeContent"
                ></div>
                <span style="padding-left: 220px">{{ item.createTime }}</span>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-col>
      </el-row>
    </div>

    <!-- 阅读新闻 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1000px"
      append-to-body
      :before-close="handleDialogClose"
    >
      <div class="news">
        <div class="title">{{ news.ntitle }}</div>
        <div class="header">
          <div class="info">
            <span style="padding-left: 20px">作者：{{ news.author }}</span>
            <span style="padding-left: 10px">阅读量：{{ news.nread }}</span>
            <span style="padding-left: 10px"
              >发布时间：{{ news.createTime }}</span
            >
          </div>
        </div>
        <div class="ql-container ql-snow">
          <div class="ql-editor" v-html="news.ndetail" />
        </div>
        <!-- <div class="content">
          <div v-html="news.ndetail" class="html"></div>
        </div> -->
      </div>
    </el-dialog>
    <!-- 分割线 -->
    <el-divider></el-divider>

    <!-- 部分数据展示卡片 -->
    <div style="height: 170px">
      <el-row :gutter="20">
        <el-col :span="10" :lg="12" style="padding-left: 20px">
          <el-card class="box-card">
            <div class="text item">
              当前共有物资<span>{{ manum.materialsum }}</span
              >批,还有<span>{{ manum.materialing }}</span
              >批可以申请
            </div>
            <div class="text item">
              可以为<span> {{ manum.disastypesum }}</span
              >种灾害提供<span>{{ manum.needtype }}</span
              >种物资帮助
            </div>
          </el-card>
        </el-col>
        <el-col :span="10" :lg="12" style="padding-left: 20px">
          <el-card class="box-card">
            <div class="text item">
              截至目前共有<span>{{ helpnum.materialed }}</span
              >人成功申请到物资帮助，还有<span>{{ helpnum.materialing }}</span
              >人正在申请物资帮助
            </div>
            <div class="text item">
              截至目前共有<span>{{ helpnum.helpnum }}</span
              >人申请帮助，有<span>{{ helpnum.helpednum }}</span
              >人成功获得帮助
            </div>
          </el-card></el-col
        >
      </el-row>
    </div>
    <div class="el-login-footer">
      <span class="el-icon-user-solid">juanQian</span>
      <span class="el-icon-phone">1588538xxxx</span>
      <span class="el-icon-message">139xxxxxx@qq.com</span>
    </div>
  </div>
</template>



<script>
import CarouselContainer from "@/components/CarouselContainer";
import {
  listCarouselimg,
  listNews,
  getNews,
  updateNews,
  getmaNum,
  gethelpNum,
  checklogin,
} from "@/api/ma/index";
import { listNotice } from "@/api/system/notice";
import { isRelogin } from "@/utils/request";

export default {
  name: "Index",
  components: {
    CarouselContainer,
  },
  //dicts: ["sys_notice_type"],
  data() {
    return {
      list: [],
      newslist1: [],
      // 通知公告列
      noticelist: [],
      // 遮罩层
      loading: true,
      // 走马灯自动播放
      flag: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 新闻列表格数据
      newsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 物质数值统计数据
      manum: {},
      //帮助数值统计数据
      helpnum: {},
      //是否登录
      islogin: false,
      //新闻
      news: {
        ntitle: null,
        author: null,
        nread: null,
        ndetail: null,
        createTime: null,
      },
    };
  },
  created() {
    this.getImgList();
    this.getNewsList();
    this.getNoticeList();
    this.getStatisticsNum();
    this.getHelpNum();
    this.isRelogin = false;
    this.islogin = false;
    this.checkLogin();
  },
  mounted() {},
  computed: {
    baseurl() {
      return process.env.VUE_APP_BASE_API;
    },
  },
  onpageshow() {
    this.getNewsList();
    this.getNoticeList();
  },
  methods: {
    getImgList() {
      listCarouselimg().then((res) => {
        for (var i = 0; i < res.rows.length; i++) {
          let data = {
            imgname: res.rows[i].imgname,
            imgpath: this.baseurl + res.rows[i].imgpath,
          };
          this.list.push(data);
          data = {};
        }
      });
    },
    getNewsList() {
      listNews().then((res) => {
        for (var i = res.rows.length - 1, j = 0; i >= 0; i--, j++) {
          if (j < 5) this.newslist1.push(res.rows[i]);
        }
      });
    },
    opennews(options, nread) {
      //修改阅读量
      updateNews({ nid: options, nread: parseInt(nread) + 1 }).then((res) => {
        getNews(options).then((res) => {
          this.news = res.data;
          //打开弹出页面
          this.open = true;
          this.title = "新闻阅读";
        });
      });
    },
    handleDialogClose() {
      this.open = false;
    },
    getNoticeList() {
      listNotice({ status: 0 }).then((res) => {
        for (var i = res.rows.length - 1, j = 0; i >= 0; i--, j++) {
          if (j < 5) this.noticelist.push(res.rows[i]);
        }
      });
    },
    getStatisticsNum() {
      getmaNum().then((res) => {
        // console.log(res)
        this.manum = res.data;
      });
    },
    getHelpNum() {
      gethelpNum().then((res) => {
        this.helpnum = res.data;
      });
    },
    //检查用户是否登录
    checkLogin() {
      checklogin().then((res) => {
        if (res.isLogin === 1) {
          this.islogin = true;
          //this.$router.push({ path: "/index1" }).catch(()=>{});
        } else {
          this.islogin = false;
          // this.$router.push({ path: this.redirect || "/login" }).catch(()=>{});
        }
      });
    },
  },
};
</script>
<style lang="scss">
.news {
  padding: 15px 0;
  padding-bottom: 54px;
}
.title {
  padding: 0 60px;
  font-size: 26px;
  font-weight: bold;
  color: #333333;
  text-align: center;
  //line-height: 100px;
}
.header {
  color: #999999;
  text-align: center;
  margin-top: 10px;
}
.content {
  margin-top: 20px;

  .html {
    padding: 0 0px;
    img {
      width: 100rpx;
      display: flex;
      justify-content: center;
      // margin-left: 20px;
      display: table-cell;
      vertical-align: middle;
    }
  }
}
.text {
  font-size: 18px;
}

.item {
  padding: 18px 0;
}

.text span {
  font-size: 22px;
  color: red;
  padding-left: 10px;
  padding-right: 10px;
}

.box-card {
  width: 100%;
  height: 150px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  //position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: black;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.title-text {
  text-align: center;
  padding-top: 5px;
  font-size: 24px;
  font-family: LiSu, STLiti, "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei",
    华文细黑, STHeiti, MingLiu;
}
.slogan {
  font-size: 20px;
  color: cornflowerblue;
  font-family: KaiTiGB2312, "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei",
    华文细黑, STHeiti, MingLiu;
  margin-left: 55%;
  font-weight: bold;
  letter-spacing: 2px;
}
.login-item {
  float: right;
  color: red;
  font-family: LiSu, MingLiu;
  font-size: 18px;
}
</style>
