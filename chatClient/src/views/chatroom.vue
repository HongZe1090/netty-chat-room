<template>
  <el-container>
    <el-header>Header</el-header>
    <el-container>
      <el-aside width="200px">
          <list/>
      </el-aside>
      <el-main v-if="currentSta.toId!=null"><chatPart/></el-main>
      <el-main v-else><el-empty style="margin-top:100px" description="现在还啥都没有哦,快点找你的好朋友们开始聊天吧"></el-empty></el-main>
    </el-container>
  </el-container>
</template>

<script>
import list from '@/components/list.vue'
import chatPart from '@/components/chatroom.vue'
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState({
      // 对话对象
      currentSta:"currentState",
    }),
  },
  components: {
    list,
    chatPart
  },
};
</script>

<style lang="css">
.el-container {
    height: 100%;
    margin-bottom: 0px;
  }
.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #8bc4ff;
    color: #303133;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #f7fbff;
    color: #333;
    text-align: center;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 0;
  }
</style>