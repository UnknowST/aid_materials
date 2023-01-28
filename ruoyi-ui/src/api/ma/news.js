import request from '@/utils/request'

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

// 新增新闻列
export function addNews(data) {
  return request({
    url: '/ma/news',
    method: 'post',
    data: data
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

// 删除新闻列
export function delNews(nid) {
  return request({
    url: '/ma/news/' + nid,
    method: 'delete'
  })
}
