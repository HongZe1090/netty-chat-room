import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  currentState:{
      toId:null,
      type:'',
      userName:'',
      image:''
  },
  myInfo: {
    userId: 1,
    userName: "",
    password: "",
    sex: "",
    image: "",
    description: "",
  },
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
