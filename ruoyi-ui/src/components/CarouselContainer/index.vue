<template>
    <div class="CarouselContainer" @mouseenter="stopAutoPlay" @mouseleave="startAutoPlay">
      <div ref="mySwiper" class="swiper-container" :id="currentIndex"  >
        <div class="swiper-wrapper">
          <div class="swiper-slide my-swiper-slide" v-for="(item,index) of slideList" :key="index">
        <img :src="item.imgpath" />
   
        </div>
        </div>
        <!-- 分页器 -->
        <div class="swiper-pagination"></div>
        <!--导航器-->
        <div class="swiper-button-prev"></div>
        <div class="swiper-button-next"></div>
      </div>
    </div>
  </template>
  <script>
  import Swiper from 'swiper'
  import "swiper/css/swiper.css";
  export default {
    name: 'CarouselContainer',
    props: ['slideList','currentIndex'],
    data(){
      return {
        currentSwiper:null,
      }
    },
    watch:{
      //slide数据发生变化时,更新swiper
      slideList:{
        deep:true,
        // eslint-disable-next-line
        handler(nv,ov){
          console.log("数据更新了")
          this.updateSwiper()
        }
      }
    },
    mounted() {
      this.initSwiper()
    },
    methods:{
      //鼠标移入暂停自动播放
      stopAutoPlay() {
         this.currentSwiper.autoplay.stop()
      },
      //鼠标移出开始自动播放
      startAutoPlay() {
        this.currentSwiper.autoplay.start()
      },
      //初始化swiper
      initSwiper(){
        // eslint-disable-next-line
        let vueComponent=this//获取vue组件实例
        //一个页面有多个swiper实例时，为了不互相影响,绑定容器用不同值或变量绑定
        this.currentSwiper = new Swiper('#'+this.currentIndex, {
          loop: true, // 循环模式选项
          autoHeight:'true',//开启自适应高度,容器高度由slide高度决定
          //分页器
          pagination: {
            el: '.swiper-pagination',
            clickable:true,//分页器按钮可点击
            type:'fraction'
          },
          on: {
            //此处this为swiper实例
            //切换结束获取slide真实下标
            slideChangeTransitionEnd: function(){
              //console.log(vueComponent.$props.currentIndex+"号swiper实例真实下标",this.realIndex)
            },
            //绑定点击事件,解决loop:true时事件丢失
            // eslint-disable-next-line
            click: function(event){
              //console.log("你点击了"+vueComponent.$props.currentIndex+"号swiper组件")
            }
          },
          //导航器
          navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
          },
          autoplay: {
            //自动播放,不同版本配置方式不同
            delay: 3000,
            stopOnLastSlide: false,
            disableOnInteraction: false
          },
          slidesPerView: 1, //视口展示slide数1
          slidesPerGroup: 1, //slide数1页一组
        })
  
      },
      //销毁swiper
      destroySwiper(){
          try {
            this.currentSwiper.destroy(true,false)
          }catch (e) {
            console.log("删除轮播")
          }
      },
      //更新swiper
      updateSwiper(){
        this.destroySwiper()
        this.$nextTick(()=>{
          this.initSwiper()
        })
      },
    },
    destroyed() {
      this.destroySwiper()
    }
  }
  </script>
  <style scoped>
    .CarouselContainer{
      background-color: gray;
    }
    /*slide样式*/
    .my-swiper-slide{
      height: 350px;
      background-color: white;
    }
    /*swiper容器样式*/
    .swiper-container {
      width: 100%;
      border: 0px solid red;
    }
    /*自定义分页器按钮被点击选中时的样式*/
    /deep/.swiper-pagination-bullet-active{
      background-color: #d5a72f !important;
      width: 20px;
    }
    /*自定义分页器按钮常规样式*/
    /deep/.swiper-pagination-bullet{
      background-color: #9624bf;
      opacity: 1;
      width: 20px;
    }
  </style>
  