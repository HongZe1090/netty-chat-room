<template>
  <el-dialog
    title="添加群组"
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
      <el-form-item label="群组ID">
        <el-input v-model="form.groupId" :value="form.groupId" />
      </el-form-item>

      <el-form-item label="群组名">
        <el-input v-model="form.groupName" />
      </el-form-item>

      <el-form-item label="群组头像">
        <el-input v-model="form.image" />
      </el-form-item>

      <el-form-item label="群组描述">
        <el-input v-model="form.description" />
      </el-form-item>

      <el-form-item label="创建人ID">
        <el-input v-model="form.admin" disabled="true" />
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
        groupId: "",
        groupName: "",
        image: "",
        description: "",
        admin: "",
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

      that.form.admin = that.myInfo.userId;

      request
        .postUrlContentType("http://121.36.199.215:8081/group/addGroup", {
          group: that.form,
        })
        .then(function (data) {
          console.log(data);
          if (data.code == 200) {
            that.$notify({
              title: "添加成功",
              message: "群组创建成功啦！...",
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
