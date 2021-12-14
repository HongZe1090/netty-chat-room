import axios from 'axios'

export function getAllLog() {
    return axios
         .get('http://localhost:8081/log/getAllLog')
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