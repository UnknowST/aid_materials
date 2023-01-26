import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";
import axios from 'axios';

// 查询用户列表
export function listUser(query) {
    return request({
        url: '/ma/user/list',
        method: 'get',
        params: query
    })
}

// 查询用户详细
export function getUser(userid) {
    return request({
        url: '/ma/user/' + parseStrEmpty(userid),
        method: 'get'
    })
}

// 新增用户
export function addUser(data) {
    return request({
        url: '/ma/user',
        method: 'post',
        data: data
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

// 修改用户
export function updateUser(data) {
    return request({
      url: '/ma/user',
      method: 'put',
      data: data
    })
  }
  
  // 删除用户
  export function delUser(userid) {
    return request({
      url: '/ma/user/' + userid,
      method: 'delete'
    })
  }
  
  // 用户密码重置
  export function resetUserPwd(userid, password) {
    const data = {
      userid,
      password
    }
    return request({
      url: '/ma/user/resetPwd',
      method: 'put',
      data: data
    })
  }
  
  // 用户状态修改
  export function changeUserStatus(userId, status) {
    const data = {
      userId,
      status
    }
    return request({
      url: '/ma/user/changeStatus',
      method: 'put',
      data: data
    })
  }
  
  // 查询用户个人信息
  export function getUserProfile() {
    return request({
      url: '/system/user/profile',
      method: 'get'
    })
  }
  
  // 修改用户个人信息
  export function updateUserProfile(data) {
    return request({
      url: '/system/user/profile',
      method: 'put',
      data: data
    })
  }
  