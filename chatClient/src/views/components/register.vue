<template>
  <el-dialog
    title="注册成员"
    :visible.sync="Visible"
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
      <el-form-item label="用户ID">
        <el-input v-model="form.userId" :value="form.userId" />
      </el-form-item>

      <el-form-item label="用户名">
        <el-input v-model="form.userName" />
      </el-form-item>

      <el-form-item label="用户密码">
        <el-input v-model="form.password" />
      </el-form-item>

      <el-form-item label="用户性别">
        <el-input v-model="form.sex" />
      </el-form-item>

      <el-form-item label="头像">
        <el-input v-model="form.image" />
      </el-form-item>

      <el-form-item label="权限">
        <el-input
          v-model="form.admin"
          placeholder="只能输入true(管理)/false(不管理)哦..."
        />
      </el-form-item>

      <el-form-item label="用户描述">
        <el-input v-model="form.description" />
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
  name: "Form",
  data() {
    return {
      form: {
        userId: "",
        userName: "",
        password: "",
        sex: "",
        image: "",
        admin: "",
        description: "",
      },
    };
  },
  props: ["Visible"],
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
        .postUrlContentType(
          "http://121.36.199.215:8081/User/addUser",
          that.form
        )
        .then(function (data) {
          console.log(data);
          if (data.code == 200) {
            that.$notify({
              title: "添加成功",
              message: "用户注册成功啦！快点找好朋友吧...",
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
