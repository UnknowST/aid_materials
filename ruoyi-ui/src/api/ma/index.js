import request from '@/utils/request'

// 查询轮播图列表
export function listCarouselimg() {
    return request({
        url: '/ma/carouselimg/list',
        method: 'get',
        params: { imgstatus: 0 }
    })
}

// 查询新闻列列表
export function listNews(query) {
    return request({
        url: '/ma/news/list',
        method: 'get',
        params: query
    })
}

// 查询新闻列详细
export function getNews(nid) {
    return request({
        url: '/ma/news/' + nid,
        method: 'get'
    })
}

// 修改新闻列
export function updateNews(data) {
    return request({
      url: '/ma/news',
      method: 'put',
      data: data
    })
  }

  //获取物资的数值统计数据
  export function getmaNum(){
    return request({
        url:'/ma/statistics/getmanum',
        method:'get'
    })
  }

  //获取帮助统计数据

  export function gethelpNum(){
    return request({
        url:'ma/statistics/gethelpnum',
        method:'get'
    })
  }