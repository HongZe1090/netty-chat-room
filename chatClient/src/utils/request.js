import axios from 'axios'
import { Notification } from 'element-ui';
/**
 * get请求
 * @param {*} url 
 * @param {*} paramsObj 
 */
export function getJSON(url, paramsObj) {
    return new Promise((resolve, reject) => {
        axios.get(
            url,
            // headers: {
            //     'Content-Type': 'application/x-www-form-urlencoded',
            //     "Authorization":'bearer '+ token  //token换成从缓存获取
            // },
            {
            params: paramsObj
        }
        ).then((res) => {
            resolve(res)
        }).catch((e) => {
            Notification.error('没有成功！')
        })
    })
}
/**
 * post传输json对象
 * @param {*} url 
 * @param {*} paramsObj 
 */
export function postJSON(url, paramsObj) {
    console.log(paramsObj)
    return new Promise((resolve, reject) => {
        let that = this
        axios({
            method: 'post',
            url: url,
            // 参数解析
            params: paramsObj,
            // headers: {
            //     "Authorization":'bearer '+ token  //token换成从缓存获取
            // }
          }).then((res) => {
            resolve(res.data)
        }).catch((e) => {
            Notification.error('添加失败啦！')  
        })
    })
}

export function postUrl(url, paramsObj) {
    return new Promise((resolve, reject) => {
        axios({
            method: 'post',
            url: url,
            // 参数解析
            params: paramsObj,
            // headers: {
            //     "Authorization":'bearer '+ token  //token换成从缓存获取
            // }
          }).then((res) => {
            console.log(res)
            resolve(res.data)
        }).catch((e) => {
        Notification.error('找不到好朋友,失联啦失联啦...')
        })
    })
}
//请求token用到的
export function postUrlContentType(url, paramsObj) {
    let that = this
    console.log(paramsObj)
    return new Promise((resolve, reject) => {
        axios({
            method: 'post',
            url: url,
            data: paramsObj,
          }).then((res) => {
            resolve(res.data)
        }).catch((e) => {
            Notification.error('失败啦！...')
        })
    })
}

export function postFile(url, data, token) {
    return new Promise((resolve, reject) => {
        axios.post(url,data, {
            headers: {
                'Content-Type': 'multipart/form-data',
                "Authorization":'bearer '+ token  //token换成从缓存获取
            },
        }).then((res) => {
            resolve(res)
        }).catch((e) => {
            reject(e)
        })
    })
}

export function copy(obj) {
    let newobj = obj.constructor === Array ? [] : {};
    if(typeof obj !== 'object'){
        return;
    }
    for(let i in obj){
        newobj[i] = typeof obj[i] === 'object' ? copy(obj[i]) : obj[i];
    }
    return newobj
}
