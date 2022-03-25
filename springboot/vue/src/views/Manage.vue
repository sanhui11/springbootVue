<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246);">
      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
    </el-aside>

    <el-container>
      <el-header style="border: 1px solid #ccc;">
        <Header :collapse="collapse" :user="user" :collapseBtnClass="collapseBtnClass"/>
      </el-header>


      <el-main>
        <!--表示当前页面的子路由会在<router-view>中展示-->
        <router-view  @refreshUser="getUser"/>
      </el-main>
    </el-container>

  </el-container>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
// import request from "@/utils/request";
import Aside from "@/views/Aside";
import Header from "@/views/Header";
import User from "@/views/User";

export default {
  name: 'Home',
  components: {Header, Aside},
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      user: {}
    }
  },
  created() {
    //从后台获取最新的user数据
    this.getUser()
  },
  methods: {
    //点击收缩按钮触发
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
      // return this.isCollapse
    },
    getUser(){
      let username=localStorage.getItem("user")? JSON.parse(localStorage.getItem("user")).username : {}
      //从后台传来的user数据
      this.request.get("/user/person/"+username).then(res=>{
        //重新赋值后台最新的user数据
          this.user=res.data
      })
    }
  }
}
</script>
