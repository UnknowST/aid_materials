import request from '@/utils/request'

// 查询轮播图列表
export function listCarouselimg(query) {
  return request({
    url: '/ma/carouselimg/list',
    method: 'get',
    params: query
  })
}

// 查询轮播图详细
export function getCarouselimg(id) {
  return request({
    url: '/ma/carouselimg/' + id,
    method: 'get'
  })
}

// 新增轮播图
export function addCarouselimg(data) {
  return request({
    url: '/ma/carouselimg',
    method: 'post',
    data: data
  })
}

// 修改轮播图
export function updateCarouselimg(data) {
  return request({
    url: '/ma/carouselimg',
    method: 'put',
    data: data
  })
}

// 删除轮播图
export function delCarouselimg(id) {
  return request({
    url: '/ma/carouselimg/' + id,
    method: 'delete'
  })
}

//修改轮播图状态
export function changeCarouseStatus(id, imgstatus) {
  const data = {
    id,
    imgstatus
  }
  return request({
    url: '/ma/carouselimg/changeStatus',
    method: 'put',
    data: data
  })
}
