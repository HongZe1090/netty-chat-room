<template>
  <div>
    <el-row>
      <el-col :span="1"
        ><el-avatar :size="45" :src="currentSta.image" />{{
      }}</el-col>
      <el-col :span="1.5" class="Name"
        ><span>{{ currentSta.userName }}</span>
        <div style="font-size: 10px">
          <span>噫这人谁来着:</span>{{ currentSta.description }}
        </div>
      </el-col>
    </el-row>

    <el-divider></el-divider>

    <el-row class="SecRow">
      <ul class="chat">
        <li class="inline" :key="res.id" v-for="res in resList">
          <div style="float: right" v-if="res.state == 0">
            <span style="font-size: 10px" v-if="res.online == false"
              >{{ res.date }}这是一条离线消息哦</span
            >
            <span class="chat-message me chat-text"
              >{{ res.message }}:{{ res.userName }}</span
            >

            <el-avatar :size="40" :src="myCircleUrl" />
          </div>

          <div
            style="float: left"
            v-else-if="res.state == 2 || res.state == 10"
          >
            <el-avatar :size="40" :src="currentSta.image" />
            <span class="chat-message chat-text"
              >{{ res.userName }}:{{ res.message }}</span
            >
            <span style="font-size: 10px" v-if="res.online == false"
              >{{ res.date }}这是一条离线消息哦</span
            >
          </div>
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
              <el-avatar class="myself" :size="50" :src="myCircleUrl"
            /></el-row>
            <el-row>{{ myInfo.userName }} </el-row>
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
import "@/css/chatRoom.less";
import * as request from "@/utils/request";
import { mapState } from "vuex";
export default {
  name: "chatPart",
  data() {
    return {
      socket: null,
      // 发送的输入内容
      inputArea: "",
      // 收到的信息 但是多次使用同一个对象储存类会造成数组元素刷新的问题，所以这里其实只有id用了
      // 0 自己的消息 2 对方的私聊消息 10 群聊消息
      response: {
        id: 0,
        state: null,
        userName: null,
        message: null,
        date: null,
        image: null,
        online: true,
      },
      // 通信端返回的通信groupid，每次点击或新用户上线都会新建，可否优化？？在前端获取之前的groupid
      newGroupId: "",
      curreGroup: {},
      resList: [],
      myCircleUrl:
        "https://img1.baidu.com/it/u=313471238,3167859783&fm=15&fmt=auto",
    };
  },
  computed: {
    ...mapState({
      // 当前用户信息
      myInfo: "myInfo",
      // 对话对象
      currentSta: "currentState",
    }),
  },
  watch: {
    currentSta: {
      handler(newVal, objVal) {
        console.log(newVal);
        this.creatGroup();
        this.pullHistory();
      },
    },
  },
  mounted() {
    this.getSocket();
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
      // 群聊私聊消息的发送，这里只是消息的发送，创建的消息发送在GreatGroup下
      // 因为这里的groupid使用的是本组件下的，需要进行判断
      let thisId;
      if (this.currentSta.type == 9) thisId = this.groupId;
      else thisId = this.currentSta.toId;

      let data = {
        type: this.currentSta.type,
        params: {
          toMessageId: thisId,
          message: this.inputArea,
          fileType: 0,
        },
      };

      this.socket.send(JSON.stringify(data));

      this.inputArea = "";
    },
    getSocket() {
      let socket;
      let that = this;

      if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
      }

      if (window.WebSocket) {
        //建立连接
        socket = new WebSocket("ws://121.36.199.215:8888/websocket");

        // 绑定事件
        socket.onmessage = function (event) {
          console.log(event.data);
          // 这里把信息从json字符串转变成为了json对象了已经
          let result = JSON.parse(event.data);

          if (result.status == 200) {
            switch (result.type) {
              //私聊群聊时会把发送的话返回
              case 0:
                that.handleSelfResponse(result);
                break;
              case 2:
                that.handleSingleMessage(result);
                break;
              case 4:
                that.handleCreateChatResponse(result);
                break;
              case 10: //群聊消息接受 其实 也是适配单聊
                that.handleGroupMessageResponse(result);
                break;
              case 12:
                //  console.log("收到心跳检测回复")
                break;
              default:
                break;
            }
          } else if (result.status == 500) {
            // 返回的请求码不为200
            that.$message({
              message: "你的朋友不在家，可能跑去种土豆啦...",
              type: "warning",
            });
          }
        };
        // socket连接
        socket.onopen = function (event) {
          console.log(event);
          that.$message({
            message: "找到通信服务器啦...",
            type: "success",
          });
          if (!window.WebSocket) {
            return;
          }
          // 注册当前用户信息
          if (socket.readyState == WebSocket.OPEN) {
            console.log("这里数据可以发送啦...");
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
            };
            socket.send(JSON.stringify(data));
          } else {
            that.$message({
              message: "Websocket连接没有开启！",
              type: "warning",
            });
          }
        };

        //socket关闭
        socket.onclose = function (event) {
          that.$message({
            message: "通信服务器又跑去种土豆啦...",
            type: "warning",
          });
        };
      } else {
        that.$message({
          message: "出大问题，快换浏览器吧...",
          type: "warning",
        });
      }
      this.socket = socket;
    },
    handleSelfResponse(result) {
      let info = result.params;

      let response = {};

      response.id = this.response.id++;
      response.state = 0;
      response.message = info.message;
      response.userName = this.myInfo.userName;
      response.date = info.date;

      this.resList.push(response);
    },
    handleSingleMessage(result) {
      let info = result.params;

      let response = {};

      response.id = this.response.id++;
      response.state = 2;
      response.message = info.message;
      response.userName = info.fromUser.userName;
      response.date = info.date;

      this.resList.push(response);
    },
    handleGroupMessageResponse(result) {
      let info = result.params;

      let response = {};

      response.id = this.response.id++;
      response.state = 10;
      response.message = info.message;
      response.userName = info.fromUser.userName;
      response.date = info.date;

      this.$message({
        message: "此时群聊在线的有" + info.nameList,
        type: "success",
      });

      this.resList.push(response);
    },
    handleCreateChatResponse(result) {
      this.$message({
        message: "成功进入" + result.groupId + "聊天室啦...",
        type: "success",
      });
      this.groupId = result.groupId;
      this.curreGroup = null;
    },
    // 当toId改变时，如果是群组则创建群组
    creatGroup() {
      console.log("后端要创建新的群组啦...");
      if (this.currentSta.type == 9) {
        // 通知创建channel群组
        console.log(this.currentSta.members);
        let data = {
          type: 3,
          params: {
            userIdList: this.currentSta.members,
          },
        };
        console.log(data);
        this.socket.send(JSON.stringify(data));
      }
    },
    // 当toId改变时，拉取历史消息
    pullHistory() {
      let that = this;
      that.resList.length = 0;

      let id;

      if (that.currentSta.userName == "你好呀") id = 85;
      else id = that.currentSta.toId;

      request
        .getJSON("http://121.36.199.215:8081/message/getInfo", {
          from: that.myInfo.userId,
          toId: id,
        })
        .then((result) => {
          let data = result.data.data;
          that.response.id = 0;

          for (let i of data) {
            that.response.id++;
            let tem_info = {
              id: null,
              state: null,
              userName: null,
              message: null,
              date: null,
              image: null,
              online: true,
            };
            tem_info.id = that.response.id;
            if (i.fromId == that.myInfo.userId) {
              tem_info.state = 0;
              tem_info.userName = that.myInfo.userName;
            } else if (i.fromId == that.currentSta.toId) {
              tem_info.state = 2;
              tem_info.userName = that.currentSta.userName;
            }

            if (i.online == true) tem_info.online = true;
            else tem_info.online = false;

            tem_info.message = i.infoContent;
            tem_info.date = i.time;
            tem_info.image = that.circleUrl;

            that.resList.push(tem_info);
          }
        });
    },
  },
};
</script>

<style lang="css">
.Name {
  font-size: 18px;
  margin-top: 16px;
  position: relative;
  bottom: 9px;
  display: grid;
  justify-items: start;
}
.myself {
  margin: 20px 0 10px 0;
}
.SecRow {
  height: 380px;
  overflow-y: auto;
}
.inline {
  width: 100%;
}
</style>
