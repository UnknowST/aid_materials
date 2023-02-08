import request from '@/utils/request'
import axios from 'axios';

// 查询 每天申请帮助的人数
export function helpnumbyday() {
    return request({
        url: '/ma/statistics/gethelpByday',
        method: 'get',
    })
}

// 查询 每日物资上传数
export function upmaterialnum() {
    return request({
        url: '/ma/statistics/getupmanum',
        method: 'get',
    })
}

// 查询 每种灾害类型的数量
export function distypenum() {
    return request({
        url: '/ma/statistics/getdistypenum',
        method: 'get',
    })
}


// 查询 每种求助类型的数量
export function getmatypenum() {
    return request({
        url: '/ma/statistics/getmatypenum',
        method: 'get',
    })
}

//获取全国地图数据

export function getdata() {
    return axios({
        method: 'get',
        url: 'https://geo.datav.aliyun.com/areas_v3/bound/100000_full.json',

    })

}


//获取省份对应

export function getProdata() {
    return request
        ({
            method: 'get',
            url: '/ma/statistics/getprodata',

        })

}