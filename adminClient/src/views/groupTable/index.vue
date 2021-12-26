<template>
  <div class="app-container">
    <el-row style="margin-bottom:10px">
      <el-input
        v-model="keyWord"
        placeholder="请输入内容"
        class="input-with-select"
      >
        <template slot="prepend">
          账号
        </template>
        <el-button
          slot="append"
          icon="el-icon-search"
          @click="searchName"
        ></el-button>
      </el-input>
    </el-row>

    <div class="mainTable">
      <el-row style="margin-bottom:10px">
        <el-button
          type="primary"
          @click="
            Visible = true;
            formOpe.formType = 0;
          "
          >增加</el-button
        >
        <el-button type="danger" @click="deleteMuti" :disabled="deleteMutiOk"
          >删除</el-button
        >
        <el-button type="success" @click="refresh">刷新</el-button>
      </el-row>

      <Form
        :Visible="Visible"
        :formOpe="formOpe"
        v-on:closeAdd="closeAddForm"
        @closeEdit="closeEditForm"
        v-on:closeForm="Visible = false"
      ></Form>
      <!--引号中的closeTable是父组件定义，前面的是子组件定义-->

      <!--将分页器和table绑定，-->
      <el-table
        stripe
        v-loading="listLoading"
        ref="checkTable"
        :data="pagelist"
        element-loading-text="Loading"
        border
        fit
        :row-key="getRowKeys"
        @selection-change="handleSelectionChange"
        highlight-current-row
      >
        <el-table-column
          :reserve-selection="true"
          type="selection"
          width="55"
          :show-overflow-tooltip="true"
          align="center"
        >
        </el-table-column>

        <el-table-column align="center" label="小组号" width="95">
          <template slot-scope="scope">
            {{ scope.row.groupId }}
          </template>
          <!-- //--- scope.row 直接取到该单元格对象，就是数组里的元素对象，即是tableData[scope.$index]
        //---.title 是对象里面的title属性的值 -->
        </el-table-column>
        <el-table-column label="小组名" width="110" align="center">
          <template slot-scope="scope">
            {{ scope.row.groupName }}
          </template>
        </el-table-column>
        <el-table-column label="描述" width="200" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column label="图片" width="200" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.image }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建人账号" width="200" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.admin }}</span>
          </template>
        </el-table-column>
        <el-table-column width="100" align="center" label="操作">
          <template slot-scope="scope">
            <!--单个编辑按钮,记录表格位置，编辑后仍然在同一个位置-->
            <el-button
              @click="editModify(scope.row, scope.$index)"
              type="primary"
              icon="el-icon-edit"
              size="small"
              circle
            ></el-button>
            <!--单个删除按钮-->
            <el-button
              @click="deleteSingle(scope.$index, scope.row)"
              type="danger"
              icon="el-icon-delete"
              size="small"
              circle
            ></el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        style="margin-top:10px;"
        align="center"
        layout="prev, pager, next"
        :total="list.length"
        :current-page.sync="currentPage"
        @current-change="handleCurrentChange"
        background
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { getAllGroups } from "@/api/log";
import Form from "./form.vue";

export default {
  components: {
    Form
  },
  data() {
    return {
      //渲染table控件
      list: [],
      listLoading: true,

      //添加与编辑子组件控件
      formOpe: {
        formType: "", //控制form表单状态，0为增加，1为修改
        formVal: {},
        formIndex: ""
      },
      Visible: false,

      //批量删除控件
      multipleSelection: [],
      deleteMutiOk: true,

      //分页控件
      currentPage: 1,

      //前端模糊搜搜控件
      keyWord: ""
    };
  },

  created() {
    this.fetchData();
  },
  //监听多选框内容，不为0则批量删除可选
  watch: {
    multipleSelection(val) {
      if (val.length == 0) this.deleteMutiOk = true;
      else this.deleteMutiOk = false;
    }
  },

  computed: {
    pagelist() {
      return this.list.slice(
        (this.currentPage - 1) * 10,
        this.currentPage * 10
      );
    }
  },

  methods: {
    //axios获取table数据
    fetchData() {
      this.listLoading = true;
      getAllGroups().then(response => {
        this.list = response;
        this.listLoading = false;
        console.log(response);
      });
    },
    //axios提交更新table数据
    requsetData() {},
    //关闭add组件
    closeAddForm(newData) {
      this.Visible = false;
      this.list.push(newData);
      this.$message("添加成功!");
    },
    //关闭edit组件
    closeEditForm(newData) {
      this.Visible = false;
      this.list.splice(
        (this.currentPage - 1) * 10 + this.formOpe.formIndex,
        1,
        newData
      );
      this.$message("修改成功!");
    },
    //多选函数，记忆储存
    getRowKeys(row) {
      return row.id;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      //   if(val.length > 0)
      //   this.list.forEach(item =>{
      //     if(item.tableIndex>=PaStart&&item.tableIndex<PaEnd){
      //       let i = 0
      //       for(let Iitem of val){
      //         if(item.tableIndex == Iitem.tableIndex){
      //           console.log(item)
      //         item.open = true
      //         break
      //         }
      //         i++
      //         }
      //         if(i >= val.length){
      //           item.open = false
      //         }
      //   }
      // })
    },
    //批量删除
    deleteMuti() {
      this.$confirm("是否确认删除数据，删除后不可恢复?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info"
      }).then(() => {
        let orVal = this.multipleSelection;
        let obVal = this.list;
        if (orVal) {
          orVal.forEach(function(item, index) {
            obVal.forEach(function(oItem, oIndex) {
              if (item.id == oItem.id) obVal.splice(oIndex, 1);
            });
          });
        }

        this.$refs.checkTable.clearSelection();
      });
    },
    //单个删除
    deleteSingle(val, row) {
      var page = this.currentPage;
      // 模态框删除按钮
      this.$confirm("是否确认删除本条数据，删除后不可恢复?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info"
      }).then(() => {
        // 此为假删除，只是删除了模态框中当前行的数据，并没有真正删除表格数据
        this.list.splice((page - 1) * 10 + val, 1);
        // 如下，接口请求则真正删除表格数据
        //  let rowId = row.id;
        // regionactivityapi.DeleteAssess({ id: rowId }).then(res => {
        //    if (res.data) {
        //     this.$message({
        //       message: "删除成功",
        //       type: "success",
        //       duration: 2000
        //       });
        //     }
        //   });
      });
    },
    //编辑组件开启
    editModify(val, index) {
      this.formOpe.formType = 1;
      this.formOpe.formVal = val;
      this.formOpe.formIndex = index;
      this.Visible = true;
    },
    //
    tableAction() {},
    //分页组件，和watch共同控制分页
    handleCurrentChange(val) {
      // 改变默认的页数
      this.currentPage = val;
      //      this.handleSelectionOpen()
    },
    //搜索控件
    searchName() {
      //自动转换封装正则表达式
      let obList = [];
      var reg = new RegExp(this.keyWord);
      console.log(reg);
      this.list.forEach(function(item, index) {
        if (item.name.match(reg)) obList.push(item);
      });

      this.list = obList;
    },
    //刷新控件
    refresh() {
      this.fetchData();
    }
  }
};
</script>

<style>
.mainTable {
  width: 1300px;
  padding: 10px;
  border: 1px rgb(245, 247, 250) solid;
  background: white;
}
.input-with-select {
  width: 500px;
  float: left;
}
.el-input-group__prepend {
  width: 50px;
  background: white;
  color: #8e9197;
}
</style>
