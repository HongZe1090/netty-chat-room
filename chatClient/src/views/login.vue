<template>
  <div class="login-container">
    <form class="login-form">
      <div class="title-container">
        <h3 class="title">你来啦,等你好久啦 Hi~ o(*￣▽￣*)ブ</h3>
      </div>

      <div class="input-warpper">
        <span></span
        ><input
          class="input-content"
          placeholder="用户名"
          type="input"
          autocomplete="off"
          v-model="userName"
        />
      </div>

      <div class="input-warpper">
        <span></span
        ><input
          class="input-content"
          placeholder="密码"
          type="password"
          autocomplete="off"
          v-model="password"
        />
      </div>

      <button class="cta" @click="toLogin()">
        <span>点击登陆</span>
        <svg width="15px" height="10px" viewBox="0 0 13 10">
          <path d="M1,5 L11,5"></path>
          <polyline points="8 1 12 5 8 9"></polyline>
        </svg>
      </button>
    </form>
  </div>
</template>

<script>
import "@/css/login.less"
import * as request from "@/utils/request"
export default {
  data() {
    return {
      userName: "",
      password: "",
      myInfo: {
        userId: "",
        userName: "",
        sex: "",
        image: "",
        description: "",
      },
    };
  },
  created() {},
  mounted() {},
  methods: {
    toLogin: function () {
      let that = this
      // that.$router.push('/wechat')
      let loginParam = {
          userName: that.userName,
          password: that.password,
      }
      request.postUrlContentType("http://localhost:8081/auth/login", loginParam).then(function(data) {
          console.log(data)
      if(data.code == 200 ){
              that.$notify({
              title: '登陆成功',
              message: '等你好久啦 Hi~ o(*￣▽￣*)ブ '+data.data.userName,
              type: 'success'
              })
              // 把当前用户的信息存到vuex里面
              that.$store.dispatch('saveUserInfo',data.data)
              // console.log(that.$store.state.myInfo)
              that.$router.push('/wechat')
      }
      if(data.code != 200 ) {
              that.$alert("进不去进不去", data.message, {
              confirmButtonText: '确定',
              })
          }
      })
    },
  },
}
</script>

<style lang="css">
</style>

                