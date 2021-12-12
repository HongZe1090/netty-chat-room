import axios from 'axios'
import { Notification } from 'element-ui';
/**
 * get请求
 * @param {*} url 
 * @param {*} paramsObj 
 */
export function getJSON(url, paramsObj, token) {
    return new Promise((resolve, reject) => {
        axios.get(url, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                "Authorization":'bearer '+ token  //token换成从缓存获取
            },
            params: paramsObj
        }).then((res) => {
            resolve(res)
        }).catch((e) => {
            reject(e)
        })
    })
}
/**
 * post传输json对象
 * @param {*} url 
 * @param {*} paramsObj 
 */
export function postJSON(url, paramsObj, token) {
    return new Promise((resolve, reject) => {
        axios.post(url, 
            paramsObj,
            {
                headers: {
                    "Authorization":'bearer '+ token  //token换成从缓存获取
                 }
            }
        ).then((res) => {
            resolve(res)
        }).catch((e) => {
            reject(e)
        })
    })
}
export function postUrl(url, paramsObj) {
    return new Promise((resolve, reject) => {
        axios({
            method: 'post',
            url: url,
            params: paramsObj,
            // headers: {
            //     "Authorization":'bearer '+ token  //token换成从缓存获取
            // }
          }).then((res) => {
              console.log(res)
            resolve(res)
        }).catch((e) => {
            reject(e)
        })
    })
}
//请求token用到的
export function postUrlContentType(url, paramsObj) {
    let that = this
    return new Promise((resolve, reject) => {
        axios({
            method: 'post',
            url: url,
            data: paramsObj,
          }).then((res) => {
            resolve(res.data)
        }).catch((e) => {
            Notification.error('服务器跑出去摘土豆啦...')
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
