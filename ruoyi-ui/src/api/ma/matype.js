import request from '@/utils/request'

// 查询求助类型列表
export function listMatype(query) {
  return request({
    url: '/ma/matype/list',
    method: 'get',
    params: query
  })
}

// 查询求助类型详细
export function getMatype(maid) {
  return request({
    url: '/ma/matype/' + maid,
    method: 'get'
  })
}

// 新增求助类型
export function addMatype(data) {
  return request({
    url: '/ma/matype',
    method: 'post',
    data: data
  })
}

// 修改求助类型
export function updateMatype(data) {
  return request({
    url: '/ma/matype',
    method: 'put',
    data: data
  })
}

// 删除求助类型
export function delMatype(maid) {
  return request({
    url: '/ma/matype/' + maid,
    method: 'delete'
  })
}
