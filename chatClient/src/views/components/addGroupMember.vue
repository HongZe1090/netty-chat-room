<template>
  <el-dialog
    title="添加群组成员"
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
        <el-input v-model="form.group" :value="form.group" />
      </el-form-item>

      <el-form-item label="好友id">
        <el-input v-model="form.friend" />
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
  name: "Form3",
  data() {
    return {
      form: {
        group: "",
        friend: "",
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
        .postJSON("http://121.36.199.215:8081/group/addGroupMember", {
          group_id: that.form.group,
          member_id: that.form.friend,
        })
        .then(function (data) {
          console.log(data);
          if (data.code == 200) {
            that.$notify({
              title: "添加成功",
              message: "群组中多了一个好朋友啦...",
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
