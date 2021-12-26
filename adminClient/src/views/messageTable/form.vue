<template>
  <el-dialog
  :title="formTitle"
  :visible.sync="Visible"
  width="35%"
  :before-close="closeForm">

  <el-form ref="form" :model="form" label-width="120px" size="medium" style="margin-right:40px;">

      <el-form-item label="操作模块"  >
        <el-input v-model="form.id" :value="form.id" :disabled="edit"/>
      </el-form-item>

      <el-form-item label="业务类型" >
        <el-input v-model="form.nickname" :disabled="edit"/>
      </el-form-item>

      <el-form-item label="操作人员">
        <el-input v-model="form.personCharJSON" />
      </el-form-item>

      <el-form-item label="操作类别">
        <el-input v-model="form.personalityJSON" />
      </el-form-item>
      
      <el-form-item label="请求url">
        <el-input v-model="form.valuesJSON" />
      </el-form-item>

      <el-form-item label="操作地址">
        <el-input v-model="form.abilityJSON" />
      </el-form-item>

      <el-form-item label="操作状态">
        <el-input v-model="form.skillJSON" />
      </el-form-item>

      <el-form-item label="操作时间">
          <el-date-picker v-model="form.created_time" type="date" placeholder="自动填充" style="width: 100%;" :disabled="true" />
      </el-form-item>

      <el-form-item>
        <el-button v-if="formOpe.formType == 0" type="primary" @click="onAddSub" style="margin:10px 50px 0 45px;">提交</el-button>
        <el-button v-else type="primary" @click="onEditSub" style="margin:10px 50px 0 45px;">提交</el-button>
        <el-button @click="closeForm">取消</el-button>
      </el-form-item>

    </el-form>

  </el-dialog>
</template>

<script>

export default {
  name: 'Form',
  data() {
    return {
      formTitle: '',
      form: {
        title: '',
        businessType: '',
        requestMethod: '',
        operName: '',
        operatorType: '',
        operUrl: '',
        operIp: '',
        operStatus: '',
        operTime: ''
      },
      edit: false
    }
  },
  props: ['Visible','formOpe'],
  watch: {
    Visible(val) {
    if(val == true)
    this.setForm();
    }
  },
  methods: {

    //设置form状态，一组件俩用
    setForm() {
    console.log(this.formOpe);
    var state = this.formOpe.formType;
    var info = this.formOpe.formVal;
    //为防止上次留下的 form 值影响，需要清空
    if ( state == 0){
      this.edit = false
      this.formTitle = "添加用户";
      for(let key in this.form)
      this.form[key] = '';
    }
    else{
      this.formTitle = "编辑用户"
      //实现深拷贝，依次（递归）复制非对象
      for(let key in info)
      this.form[key] = info[key]
      this.edit = true
    }

    },

    //添加回传父组件
    onAddSub() {
      let newData = this.form;
      this.$confirm("是否确认添加本条数据?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info"
      }).then(() => {
        newData.created_time = new Date().toLocaleString().replace(/年|月/g, "-").replace(/日/g, " "); 
        this.$emit('closeAdd',newData);
      })
    },

    //编辑回传父组件
    onEditSub() {
      //用瑕疵，如果没有改也会提示，缺少表单校验
      let newData = this.form;
      this.$confirm("是否确认修改本条数据?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info"
      }).then(() => {
        newData.modify_time = new Date().toLocaleString().replace(/年|月/g, "-").replace(/日/g, " "); 
        this.$emit('closeEdit',newData);
      })

    },

    //关闭
    closeForm() {
      //this.Visible = false; 不能直接修改props中的值,同时要和父组件中的效果统一，故可以调用父组件改变重新传参
      this.$emit('closeForm');
    }
  }
}
</script>
