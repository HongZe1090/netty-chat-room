<template>
     <div class="login-container">
             <form class="login-form">
                <div class="title-container">
                    <h3 class="title">你来啦,等你好久啦 Hi~ o(*￣▽￣*)ブ</h3>
                </div>

                <div class="input-warpper">
                 <span></span><input class="input-content" placeholder="用户名" type="input" autocomplete="off" v-model="userName">
                 </div>

                 <div class="input-warpper">
                 <span></span><input class="input-content" placeholder="密码" type="password" autocomplete="off" v-model="password">
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
import  "@/css/login.less"
import * as request from "@/utils/request";
import { mapActions } from 'vuex';
export default {
    data() {
        return {
            userName: "",
            password: "",
        }
    },
    created() {
        
    },
    mounted() {
        
    },
    methods: {
        ...mapActions({
            saveUserInfo: 'saveUserInfo'
        }),
        toLogin: function() {
            let me = this;
            let loginParam = {
                userName: me.userName,
                password: me.password,
            }
            request.postUrlContentType("http://localhost:8081/auth/login", loginParam).then(function(res) {
                if (res.data) {
                   //再去请求userId
                   let userParams = {
                        userName: me.userName,
                   }
                   let token = res.data.access_token;
                   request.postJSON("/wechat/login/getUser", userParams, token).then(function(res) {
                        // me.saveUserInfo(res.data.obj);
                        // let params = {
                        //     userId: res.data.obj.userId
                        // }
                        // localStorage.setItem("access_token_"+params.userId, token)
                        // let encodeUrl = window.encodeURI(JSON.stringify(params));
                        // me.$router.push('/wechat?urlParams='+encodeUrl)
                   })
                } else {
                    alert("登录失败，密码错误！")
                }
            })
        }
    },
}
</script>

<style lang="css">

</style>