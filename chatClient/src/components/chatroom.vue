<template>

  <div>
    <el-row>
      <el-col :span="1"><el-avatar :size="45" :src="circleUrl" /></el-col>
      <el-col :span="1.5" class="Name">{{currentSta.userName}}</el-col>
    </el-row>

    <el-divider></el-divider>

    <el-row class="SecRow">
      <ul class="chat">
        <li class="inline" :key="res.id" v-for="res in resList">
          <el-avatar :size="40" :src="circleUrl" />
          <span class="chat-message chat-text">{{res.message}}</span>
        </li>
      </ul>
    </el-row>

    <el-divider></el-divider>

    <el-row>
      <el-col :span="21">
        <el-input
          type="textarea"
          :rows="6"
          placeholder="请输入内容"
          v-model="inputArea"
        >
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-row>
          <el-col>
            <el-row>
            <el-avatar class="myself" :size="50" :src="circleUrl"
          /></el-row>
          <el-row>{{myInfo.userName}}
          </el-row>
          </el-col>
        </el-row>
        <el-row>
          <el-button type="success" plain @click="send()">发送</el-button>
          <el-button type="warning" plain>撤回</el-button>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import "@/css/chatRoom.less"
import { mapState } from 'vuex'
export default {
  name: "chatPart",
  data() {
    return {
      socket: null,
      // 发送的输入内容
      inputArea: "",
      // 收到的信息 但是多次使用同一个对象储存类会造成数组元素刷新的问题，所以这里其实只有id用了
      response: {
        id:0,
        state:null,
        userName:null,
        message:null,
        date:null,
        image:null
      },
      resList:[],
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
    }
  },
computed: {
    ...mapState({
      // 当前用户信息
      myInfo:"myInfo",
      // 对话对象
      currentSta:"currentState",
    }),
  },
  mounted() {
    this.getSocket()
  },
  methods: {
    // 连接关闭的回调函数
    onClose() {
      let that = this;
      this.socket.onclose = function (event) {
        that.$message({
          message: "再见哦，我要去摘土豆啦...",
          type: "warning",
        });
      };
    },
    send() {
      let data = {
      type:this.currentSta.type,
      params:{
        toMessageId:this.currentSta.toId,
        message:this.inputArea,
        fileType:0
        }
      }

      this.socket.send(JSON.stringify(data));
      
    },
    getSocket() {
      let socket;
      let that = this;

      if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
      }

      if (window.WebSocket) {
        //建立连接
        socket = new WebSocket("ws://localhost:8888/websocket")  

        // 绑定事件
        socket.onmessage = function (event) {
          console.log(event.data);
            // 这里把信息从json字符串转变成为了json对象了已经
           let result = JSON.parse(event.data)

           if (result.status == 200) {
             switch(result.type) {
               //私聊群聊时会把发送的话返回
               case 0:
                 that.handleSelfResponse(result)
                 break
               case 2:
                 that.handleSingleMessage(result)
                 break
               case 4:
                 that.handleCreateChatResponse(result)
                 break
               case 10: //群聊消息接受 其实 也是适配单聊
                 that.handleGroupMessageResponse(result);
                 break;
               case 12:
                 console.log("收到心跳检测回复")
                 break;
                 default:
                 break;
            }
          } else if (result.status == 404) {
            // 返回的请求码不为200
            that.$message({
              message: "发送失败 对方不在线",
              type: "warning",
            })
           }
         }
        // socket连接
        socket.onopen = function (event) {
          console.log(event)
          that.$message({
            message: "找到通信服务器啦...",
            type: "success",
          })
          if (!window.WebSocket) {
          return
          }
          // 注册当前用户信息
          if (socket.readyState == WebSocket.OPEN) {
            console.log("这里数据可以发送啦...")
            let data = {
              type: 7,
              params: {
                // 用户id
                userId: that.myInfo.userId,
                // 用户名
                userName: that.myInfo.userName,
                // 密码
                password: that.myInfo.password,
                // 性别
                sex: that.myInfo.sex,
                // 头像路径
                image: that.myInfo.image,
              },
            }
            socket.send(JSON.stringify(data));
          } else {
            that.$message({
              message: "Websocket连接没有开启！",
              type: "warning",
            });
          }
        }

        //socket关闭
        socket.onclose = function (event) {
          that.$message({
          message: "通信服务器又跑去种土豆啦...",
          type: "warning",
        })
        }
      } else {
        that.$message({
          message: "出大问题，快换浏览器吧...",
          type: "warning",
        })
      }
      this.socket = socket;
    },
    handleSelfResponse(result){
      let info = result.params

      let response = {}

      response.id = this.response.id++
      response.state = 0
      response.message = info.message
      response.userName = this.myInfo.userName
      response.date = info.date
     
      this.resList.push(response)
    },
    handleSingleMessage(result){
      let info = result.params

      let response = {}

      response.id = this.response.id++
      response = 2
      response = info.message
      response = info.fromUser.userName
      response = info.date

      this.resList.push(response)
    }
  },
};
</script>

<style lang="css">
.Name {
  font-size: 18px;
  margin-top: 16px;
}
.myself {
  margin: 20px 0 10px 0;
}
.SecRow {
  height: 380px;
  overflow-y: auto;
}
.inline{
  display: flex;
}
</style>
