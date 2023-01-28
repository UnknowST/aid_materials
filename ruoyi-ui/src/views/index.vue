<template>
  <div id="app">
    <!--传递不同的currentIndex 作为区分不同swiper组件的动态id-->
    <CarouselContainer :slide-list="list" currentIndex="1"></CarouselContainer>
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

    <!-- 添加或修改新闻列对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="800px"
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
        <div class="content">
          <div v-html="news.ndetail" class="html"></div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>


<script>
import CarouselContainer from "@/components/CarouselContainer";
import { listCarouselimg, listNews, getNews, updateNews } from "@/api/ma/index";
import { listNotice } from "@/api/system/notice";

export default {
  name: "Index",
  components: {
    CarouselContainer,
  },
  dicts: ["sys_notice_type"],
  data() {
    return {
      list: [],
      newslist1: [],
      // 通知公告列
      noticelist: [],
      // 遮罩层
      loading: true,

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
  font-size: 20px;
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
</style>
