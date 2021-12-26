import axios from 'axios'

export function getAllLog() {
    return axios
         .get('http://121.36.199.215:8081/log/getAllLog')
         .then(function(res){
          return new Promise((resolve,reject) =>{
            // console.log(res)
            if(res.status == 200)
              resolve(res.data.data)
            else{
              reject('error')
               }
            }
         )
        })
        .catch(function (error) { // 请求失败处理
          reject(error)
      }); 
}

export function getAllMessage() {
    return axios
         .get('http://121.36.199.215:8081/message/getAllInfo')
         .then(function(res){
          return new Promise((resolve,reject) =>{
            // console.log(res)
            if(res.status == 200)
              resolve(res.data.data)
            else{
              reject('error')
               }
            }
         )
        })
        .catch(function (error) { // 请求失败处理
          reject(error)
      }); 
}

export function getAllUser() {
    return axios
         .get('http://121.36.199.215:8081/User/getUser')
         .then(function(res){
          return new Promise((resolve,reject) =>{
            console.log(res)
            if(res.status == 200)
              resolve(res.data.data)
            else{
              reject('error')
               }
            }
         )
        })
        .catch(function (error) { // 请求失败处理
          reject(error)
      }); 
}


export function getAllGroups() {
    return axios
         .get('http://121.36.199.215:8081/group/getAllGroup')
         .then(function(res){
          return new Promise((resolve,reject) =>{
            console.log(res)
            if(res.status == 200)
              resolve(res.data.data)
            else{
              reject('error')
               }
            }
         )
        })
        .catch(function (error) { // 请求失败处理
          reject(error)
      }); 
}