import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  // 群聊私聊消息 0 为群聊，1 为私聊
  // 私聊的具体成员在chatroom组件中存储，每次新建群组都会获取
  currentState:{
    toId:null,
    type:'',
    userName:'',
    image:'',
    members:'',
    description:'',
  },
  myInfo: {
    userId: null,
    userName: "",
    password: "",
    sex: "",
    image: "",
    description: "",
  }
  },
  mutations: {
    commitMyInfo: function(state, info) {
      state.myInfo = info;
      console.log(state.myInfo)
    },
    commitcurrentState: function(state, info){
      state.currentState = info
      console.log(state.currentState)
    }
  },
  actions: {
    chooseAccept: function(state, info) {
     this.commit("commitcurrentState", info)
    },
    saveUserInfo: function(state, info) {
      this.commit("commitMyInfo", info)
    },
  },
  modules: {
  }
})
