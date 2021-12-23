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
          <el-menu-item :key="gro.groupId" v-for="gro in groups" @click="beignGroupChat(gro)">{{gro.groupName}}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-col>
</template>

<script>
import * as request from "@/utils/request"
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
      group:{
          groupId:'',
          groupName:'',
          image:'',
          description:'',
          members:''
      },
      friends:[],
      groups:[],
      currentState:{
          toId:'',
          type:''
      }
    };
  },
  created(){
  },
  mounted(){
    let that = this
    let id = that.$store.state.myInfo.userId
    request.postUrl("http://localhost:8081/userFriend/getAllFriends",{userId:id}).then(function(result) {
                        for(let i of result.data){
                          that.friend = i
                          that.friends.push(that.friend)
                        }
                   })
    this.groups.push({groupId:1,groupName:'hhhh',image:null,description:'this is testing',members:'1,2,3,'})
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
        type:1,
        userName:friend.userName,
        image:friend.image
      })
    },
    beignGroupChat(group){
        this.$store.dispatch("chooseAccept",{
        type:0,
        toId:group.groupId,
        userName:group.groupName,
        image:group.image,
        description:group.description,
        members:group.members
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