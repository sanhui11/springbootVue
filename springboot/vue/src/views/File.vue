<template>
  <div>
    <!-- 加在el标签中，会有一定的样式-->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称搜索" suffix-icon="el-icon-search" class="mr-5"
                v-model="type"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-upload action="http://localhost:9090/file/upload" style="display: inline-block" :show-file-list="false"  :on-success="handleUploadFileSuccess">
        <el-button type="primary" class="ml-5">上传文件<i class="el-icon-bottom"></i></el-button>
      </el-upload>

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
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="id" label="ID" ></el-table-column>
      <el-table-column prop="name" label="文件名" ></el-table-column>
      <el-table-column prop="type" label="类型" ></el-table-column>
      <el-table-column prop="size" label="大小(kb)"></el-table-column>
      <el-table-column  label="下载">
        <template slot-scope="scope">
          <el-button type="primary" @click="download(scope.row.url)">下载</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="enable" label="启用">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc" @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="handle" label="操作">
        <template slot-scope="scope">
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
  </div>
</template>

<script>
export default {
  name: "File",
  data(){
    return {
      tableData:[],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      type: "",
      multipleSelection: [],
      headerBg: 'headerBg',
      dialogFormVisible: false,
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      // let user=localStorage.getItem
      // if()
      this.request.get("/file/page", {
        params: {
          type: this.type,
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res => {
        this.tableData = res.data.records
        //分页的时候要注意这个total
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
    del(id) {
      this.request.delete("/file/" + id).then(res => {
        if (res.code==='200') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    delBatch() {
      //记着这固定的写法
      let ids = this.multipleSelection.map(v => v.id)  //[{},{},{}]==>[1,2,3]
      //delete不支持对象类型
      this.request.post("/file/del/batch", ids).then(res => {
        if (res.code==='200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.type = ""
      this.load()
    },
    handleSelectionChange(val) {
      //多选的配套属性
      // console.log(val);
      this.multipleSelection = val
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
    handleUploadFileSuccess(res){
      this.load()
    },
    download(url){
      window.open(url);
    },
    changeEnable(row){
      //row包含当前行的所有数据
      this.request.post("/file/update",row).then(res =>{
        if (res.code==='200'){
          this.$message.success("更新成功")
          this.load()
        }
      })
    }
  },
}
</script>

<style scoped>
.headerBg {
  background: #eee !important;
}
</style>