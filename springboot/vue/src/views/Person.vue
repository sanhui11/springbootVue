<template>
  <el-card style="width: 500px">
    <el-form label-width="80px" size="small">
      <el-upload
          style="text-align: center;margin-bottom: 20px"
          class="avatar-uploader"
          action="http://localhost:9090/file/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
      >
        <img v-if="form.avatar" :src="form.avatar" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>

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
      <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  mounted() {
    this.getUser().then(res=>{
      console.log(res);
      this.form=res
    })
  },
  methods: {
    async getUser(){
      return (await this.request.get("/user/person/"+this.user.username)).data
    },
    save() {
      this.request.post("/user",this.form).then(res=>{
        if (res.code==='200'){
          this.$message.success("修改成功")
          this.$emit("refreshUser")
        }else {
          this.$message.error("修改失败")
        }
      })
    },
    handleAvatarSuccess(res) {
      this.form.avatar = res
    }
  }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 20px;
  color: #8c9394;
  width: 158px;
  height: 158px;
  line-height: 158px;
  text-align: center;
}

.avatar {
  width: 158px;
  height: 158px;
  display: block;
}
</style>