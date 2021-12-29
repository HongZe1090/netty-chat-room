<template>
  <el-row>
    <el-col :span="1" style="position: relative; bottom: 15px">
      <el-avatar class="myself" :size="50" :src="circleUrl"
    /></el-col>
    <el-col :span="3" class="Name">
      <span style="line-height: initial">{{ myInfo.userName }}</span>
      <div style="font-size: 10px; line-height: initial">
        噫我是谁来着：{{ myInfo.description }}
      </div>
    </el-col>

    <el-col :span="18">
      你好你好~ o(*￣▽￣*)ブ,这是Netty高性能聊天室1.0版
    </el-col>

    <el-col :span="2">
      <el-dropdown>
        <span class="el-dropdown-link">
          看这里看这里<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-on:click.native="Visible = true"
            ><i class="el-icon-edit"></i>添加好友</el-dropdown-item
          >
          <el-dropdown-item v-on:click.native="Visible1 = true"
            ><i class="el-icon-delete"></i>删除好友
            {{ Visible1 }}</el-dropdown-item
          >
          <el-dropdown-item v-on:click.native="Visible2 = true"
            ><i class="el-icon-check"></i>添加群组{{
              Visible2
            }}</el-dropdown-item
          >
          <el-dropdown-item
            ><i class="el-icon-user"></i>添加成员</el-dropdown-item
          >
          <el-dropdown-item divided>这里是已经完成的相关操作</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-col>

    <Form :Visible="Visible" v-on:closeForm="Visible = false"></Form>
    <Form1 :Visible="Visible1" v-on:closeForm="Visible1 = false"></Form1>
    <Form2 :Visible="Visible2" v-on:closeForm="Visible2 = false"></Form2>
  </el-row>
</template>

<script>
import * as request from "@/utils/request";
import { mapState } from "vuex";
import Form from "../views/components/addFriend.vue";
import Form1 from "../views/components/deleteFriend.vue";
import Form2 from "../views/components/addGroup.vue";
export default {
  name: "header",
  components: {
    Form,
    Form1,
    Form2,
  },
  data() {
    return {
      Visible: false,
      Visible1: false,
      Visible2: false,
      Visible3: false,
      circleUrl:
        "https://img1.baidu.com/it/u=313471238,3167859783&fm=15&fmt=auto",
    };
  },
  computed: {
    ...mapState({
      // 当前用户信息
      myInfo: "myInfo",
    }),
  },
  created() {},
};
</script>

<style lang="css">
.Name {
  font-size: 18px;
  margin-top: 15px;
  position: relative;
  bottom: 2px;
  display: grid;
  justify-items: start;
}
.el-dropdown-link {
  cursor: pointer;
  color: #333;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>
