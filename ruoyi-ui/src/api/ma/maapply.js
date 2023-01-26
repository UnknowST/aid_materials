import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";
import axios from 'axios';

// 获取灾害类型数据
export function listDistype(query) {
    return request({
      url: '/ma/distype/list',
      method: 'get',
      params: query
    })
  }

// 获取需求类型数据
export function listMatype(query) {
    return request({
      url: '/ma/matype/list',
      method: 'get',
      params: query
    })
  }

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

  // 新增求助记录
export function addHelp(data) {
    return request({
      url: '/ma/help',
      method: 'post',
      data: data
    })
  }