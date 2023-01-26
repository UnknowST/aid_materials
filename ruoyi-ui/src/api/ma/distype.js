import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listDistype(query) {
  return request({
    url: '/ma/distype/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getDistype(disid) {
  return request({
    url: '/ma/distype/' + disid,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addDistype(data) {
  return request({
    url: '/ma/distype',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateDistype(data) {
  return request({
    url: '/ma/distype',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delDistype(disid) {
  return request({
    url: '/ma/distype/' + disid,
    method: 'delete'
  })
}
