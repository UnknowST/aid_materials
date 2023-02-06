import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";
import axios from 'axios';


  // 获取省市县 三级联动数据
  export function getPosition() {
    return axios({
         method: 'get',
         url: 'https://restapi.amap.com/v3/config/district?parameters',
         params: { key: '2ae4bcde74c55e6efb8258887bb01737',
         keyWords: "中国",
         subdistrict: 3,  }    //GET参数要通过params属性提供
         }) 
  
  }

  
// 查询物资列表
export function listMaterial(query) {
    return request({
      url: '/ma/material/list',
      method: 'get',
      params: query
    })
  }
  
// 查询登录用户的物资列表
export function mylistMaterial(query){
  return request({
    url:'ma/material/mylist',
    method:'get',
    params:query
  })
}

  // 查询物资详细
  export function getMaterial(mid) {
    return request({
      url: '/ma/material/' + mid,
      method: 'get'
    })
  }
  
  // 新增物资
  export function addMaterial(data) {
    return request({
      url: '/ma/material',
      method: 'post',
      data: data
    })
  }
  
  // 修改物资
  export function updateMaterial(data) {
    return request({
      url: '/ma/material',
      method: 'put',
      data: data
    })
  }
  
  // 删除物资
  export function delMaterial(mid) {
    return request({
      url: '/ma/material/' + mid,
      method: 'delete'
    })
  }