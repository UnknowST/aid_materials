import request from '@/utils/request'

// 查询灾害类型列表
export function listDistype(query) {
  return request({
    url: '/ma/distype/list',
    method: 'get',
    params: query
  })
}

// 查询灾害类型详细
export function getDistype(disid) {
  return request({
    url: '/ma/distype/' + disid,
    method: 'get'
  })
}

// 新增灾害类型
export function addDistype(data) {
  return request({
    url: '/ma/distype',
    method: 'post',
    data: data
  })
}

// 修改灾害类型
export function updateDistype(data) {
  return request({
    url: '/ma/distype',
    method: 'put',
    data: data
  })
}

// 删除灾害类型
export function delDistype(disid) {
  return request({
    url: '/ma/distype/' + disid,
    method: 'delete'
  })
}
