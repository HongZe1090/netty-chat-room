import Vue from 'vue'
import VueRouter from 'vue-router'
import chatroom from '../views/chatroom.vue'
import Login from '../views/login.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: "/wechat",
    name: "chatRoom",
    component: chatroom
  },
]

const router = new VueRouter({
  // mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

export default router
