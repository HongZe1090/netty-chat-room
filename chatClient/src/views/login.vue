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
            let that = this
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

request.postUrl("http://localhost:8081/userFriend/getAllFriends",1).then(function(res) {
                        // me.saveUserInfo(res.data.obj);
                        // let params = {
                        //     userId: res.data.obj.userId
                        // }
                        // localStorage.setItem("access_token_"+params.userId, token)
                        // let encodeUrl = window.encodeURI(JSON.stringify(params));
                        // me.$router.push('/wechat?urlParams='+encodeUrl)
                   })

                    that.$router.push('/wechat')

                       

            }
            if(data.code != 200 ) {
                    that.$alert(data.message, '进不去进不去', {
                    confirmButtonText: '确定',
                    })
                }
                   //再去请求userId
                   let userParams = {
                        userName: that.userName,
                   }
                   let token = res.data.access_token;
            })
        }
    },
}
</script>

<style lang="css">

</style>