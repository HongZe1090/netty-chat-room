<template>
  <el-col>
    <div class="thisTitle">你的好朋友们在这里</div>
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#5fafff"
      text-color="#303133"
      active-text-color="#1389ff"
    >
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-user"></i>
          <span>好友</span>
        </template>
        <el-menu-item-group>
          <el-menu-item :key="friend.userId" v-for="friend in friends" @click="beignChat(friend)">{{friend.userName}}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-help"></i>
          <span>群组</span>
        </template>
        <el-menu-item-group>
          <el-menu-item :key="friend.userId" v-for="friend in friends">{{friend.userName}}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-col>
</template>

<script>
export default {
  name: "list",
  data() {
    return {
      friend:{
          userId:'',
          userName:'',
          sex:'',
          image:'',
          description:''
      },
      friends:[],
      group:{

      },
      groups:[],
      currentState:{
          toId:'',
          type:''
      }
    };
  },
  created(){
    this.friend.userId=1
    this.friend.userName='123'
    this.friend.sex='男',
    this.friend.image='',
    this.friend.description='这是个好人'
    this.friends.push(this.friend)
    this.friends.push(this.friend)
    this.friends.push(this.friend)
  },
  mounted(){

  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath)
    },
    beignChat(friend){
      this.$store.dispatch("chooseAccept",{
        toId:friend.userId,
        type:0,
        userName:friend.userName,
        image:friend.image
      })
    }
  },
};
</script>

<style lang="css">
.thisTitle {
  font-family: sans-serif;
  background: #389bff;
  font-size: 15px;
  line-height: 50px;
  font-weight: 800;
}
</style>