import request from '@/utils/request'

// 查询物资帮助列表
export function listHelped(query) {
  return request({
    url: '/ma/helped/list',
    method: 'get',
    params: query
  })
}

// 查询物资帮助详细
export function getHelped(huid) {
  return request({
    url: '/ma/helped/' + huid,
    method: 'get'
  })
}

// 新增物资帮助
export function addHelped(data) {
  return request({
    url: '/ma/helped',
    method: 'post',
    data: data
  })
}

// 修改物资帮助
export function updateHelped(data) {
  return request({
    url: '/ma/helped',
    method: 'put',
    data: data
  })
}

// 删除物资帮助
export function delHelped(huid) {
  return request({
    url: '/ma/helped/' + huid,
    method: 'delete'
  })
}
