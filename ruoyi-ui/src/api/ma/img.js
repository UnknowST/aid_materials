import request from '@/utils/request'

// 查询图片列表
export function listImg(query) {
  return request({
    url: '/system/img/list',
    method: 'get',
    params: query
  })
}

// 查询图片详细
export function getImg(imgid) {
  return request({
    url: '/ma/img/' + imgid,
    method: 'get'
  })
}

// 新增图片
export function addImg(data) {
  return request({
    url: '/ma/img',
    method: 'post',
    data: data
  })
}

// 修改图片信息
export function updateImg(data) {
  return request({
    url: '/ma/img',
    method: 'put',
    data: data
  })
}

// 删除图片
export function delImg(imgid) {
  return request({
    url: '/ma/img/' + imgid,
    method: 'delete'
  })
}
