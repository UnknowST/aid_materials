import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listMatype(query) {
  return request({
    url: '/ma/matype/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getMatype(maid) {
  return request({
    url: '/ma/matype/' + maid,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addMatype(data) {
  return request({
    url: '/ma/matype',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateMatype(data) {
  return request({
    url: '/ma/matype',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delMatype(maid) {
  return request({
    url: '/ma/matype/' + maid,
    method: 'delete'
  })
}
