// vue项目入口文件 首先加载这个文件
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './css/reset.css'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
