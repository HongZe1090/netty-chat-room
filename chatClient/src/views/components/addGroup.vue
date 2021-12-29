<template>
  <el-dialog
    title="添加群组"
    :visible.sync="Visible2"
    width="35%"
    :before-close="closeForm"
  >
    <el-form
      ref="form"
      :model="form"
      label-width="120px"
      size="medium"
      style="margin-right: 40px"
    >
      <el-form-item label="好友ID">
        <el-input v-model="form.ID" :value="form.ID" />
      </el-form-item>

      <el-form-item label="好友名">
        <el-input v-model="form.nickname" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onAddSub">提交</el-button>
        <el-button @click="closeForm()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import { mapState } from "vuex";
import * as request from "@/utils/request";
export default {
  name: "Form2",
  data() {
    return {
      form: {
        ID: "",
        nickname: "",
      },
    };
  },

  props: ["Visible2"],
  computed: {
    ...mapState({
      // 当前用户信息
      myInfo: "myInfo",
    }),
  },
  methods: {
    closeForm() {
      this.$emit("closeForm");
    },
    onAddSub() {
      let that = this;

      request
        .getJSON("http://121.36.199.215:8081/userFriend/addFriend", {
          userId: this.myInfo.userId,
          friendId: this.form.ID,
        })
        .then(function (data) {
          console.log(data);
          if (data.data.code == 200) {
            that.$notify({
              title: "添加成功",
              message: "多了一个好朋友啦...",
              type: "success",
            });
          } else {
            that.$notify({
              title: "添加失败",
              message: "加不上加不上",
              type: "warning",
            });
          }
        });

      this.$emit("closeForm");
    },
  },
};
</script>
