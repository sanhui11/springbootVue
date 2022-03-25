<template>
  <div>
    <!-- 加在el标签中，会有一定的样式-->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称搜索" suffix-icon="el-icon-search" class="mr-5"
                v-model="username"></el-input>
      <el-input style="width: 200px" placeholder="请输入邮箱搜索" suffix-icon="el-icon-message" class="mr-5"
                v-model="email"></el-input>
      <el-input style="width: 200px" placeholder="请输入地址搜索" suffix-icon="el-icon-position" class="mr-5"
                v-model="address"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5 mr-5"
          confirm-button-text='好的'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除吗"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload
          action="http://localhost:9090/import" style="display: inline-block;" :show-file-list="false" accept="xlsx" :on-success="handleImportSuccess">
        <el-button type="primary" @click="handleImportSuccess">导入<i class="el-icon-top"></i></el-button>
      </el-upload>
      <el-button class="ml-5" type="primary" @click="exp">导出<i class="el-icon-bottom"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="username" label="用户名" width="140"></el-table-column>
      <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="handle" label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='好的'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除<i class="el-icon-delete"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-size="pageSize"
          :page-sizes="[2, 5, 10, 20]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="头像">
          <el-input v-model="form.avatar" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      email: "",
      address: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg'
    }
  },
  mounted() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
      //请求分页参数
      // fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize)
      //     .then(res=>res.json()).then(res=>{
      //   console.log(res)
      //   this.tableData=res.data.list
      //   this.total=res.total
      // })
    },
    reset() {
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },
    //form里面的id应该是 handleEdit(row)从这里隐式获取  @click="handleEdit(scope.row)"
    save() {
      this.request.post("/user", this.form).then(res => {
        if (res.code==='200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    del(id) {
      this.request.delete("/user/" + id).then(res => {
        if (res.code==='200') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  //[{},{},{}]==>[1,2,3]
      //delete不支持对象类型
      this.request.post("/user/del/batch", ids).then(res => {
        if (res.code==='200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    cancel() {
      this.dialogFormVisible = false
      this.load()
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleSelectionChange(val) {
      //多选的配套属性
      // console.log(val);
      this.multipleSelection = val
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(PageNum) {
      console.log(PageNum)
      this.pageNum = PageNum
      this.load()
    },
    exp(){
      window.open("http://localhost:9090/user/export")
    },
    handleImportSuccess(){
      //导入这里有些格式错了 后台按照updateTime来进行降序排序  导入的数据updatetime有误
      this.$message.success("导入成功")
      this.load()
    }
  },

}
</script>

<style scoped>

</style>