<template>
  <div class="wrapper">
    <div
        style="margin: 220px auto;background-color: #fff;width: 400px;height: 400px;padding: 20px;border-radius: 10px ">
      <div style="margin: 10px 0;text-align: center;font-size: 24px"><b>注  册</b></div>
      <el-form status-icon :model="ruleForm" :rules="rules" ref="ruleForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 5px 0;" prefix-icon="el-icon-user" placeholder="用户名"
                    v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 5px 0;" prefix-icon="el-icon-lock" show-password placeholder="密码"
                    v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
          <el-input size="medium" style="margin: 5px 0;" prefix-icon="el-icon-lock" show-password  placeholder="确认密码"
                    v-model="ruleForm.checkPass"></el-input>
        </el-form-item>
        <el-form-item prop="code" style="margin: 5px 0; text-align: center; display: inline-block">
          <el-input size="medium" type="text" v-model="ruleForm.code" auto-complete="off"
                    style="width: 220px;height: 36px"
                    placeholder="验证码"></el-input>
          <span @click="clickAuthImg()" class="login-code-img">
            <span :style="reOneStyle">{{ reOne }}</span>
            <span :style="reTwoStyle">{{ reTwo }}</span>
            <span :style="reThreeStyle">{{ reThree }}</span>
            <span :style="reFourStyle">{{ reFour }}</span>
          </span>
        </el-form-item>
        <div style=" margin: 20px 0;text-align: right">
          <el-button type="primary" @click="login" size="small" autocomplete="off">登录</el-button>
          <el-button type="primary" size="small" @click="register" autocomplete="off">注册</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "register",
  data() {
    let checkUsername = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('请输入用户名'));
      } else {
        callback();
      }
    };
    let Password = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    let checkPass=(rule,value,callback)=>{
      if (value===''){
        callback(new Error('请输入密码密码'));
      }else if (value !== this.ruleForm.password){
        callback(new Error('两次输入的密码不同'))
      }else {
        callback();
      }
    };
    let checkCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else if (value !== this.code) {
        callback(new Error('验证码错误!'));
      } else {
        callback();
      }
    };
    return {
      code: "",
      reOne: "",
      reTwo: "",
      reThree: "",
      reFour: "",
      reOneStyle: "",
      reTwoStyle: "",
      reThreeStyle: "",
      reFourStyle: "",
      //ruleForm里面需要这些定义的参数
      ruleForm: {
        username: '',
        password: '',
        checkPass: '',
        code: ''
      },

      rules: {
        username: [
          {validator: checkUsername, trigger: 'blur'}
        ],
        code: [
          {validator: checkCode, trigger: 'blur'}
        ],
        password: [
          {validator: Password, trigger: 'blur'}
        ],
        checkPass:[
          {validator: checkPass,trigger: 'blur'}
        ]
      }
    };
  },
  created() {
    this.createCode()
  },
  methods: {
    register() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {  //表单校验合法  前端校验通过才开始发请求
          // if (this.ruleForm.password!==this.ruleForm.checkPass){
          //   this.$message.success("两次输入的密码不一致");
          // }
          let username=this.ruleForm.username
          let password=this.ruleForm.password
          let data = {username,password}
          this.request.post("/user/register", data).then(res => {
            if (res.code === '200') {
              localStorage.setItem("user", JSON.stringify(res.data));
              this.$router.push("/")
              this.$message.success("注册成功");
            } else {
              this.$message.error(res.msg);
            }
          })
        }
      })
    },
    login(){
      this.$router.push("/login")
    },
    createCode() {
      let code = "";
      let codeLength = 4;
      let random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c', 'd', 'e', 'f', 'g',
          'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');//随机数
      for (let i = 0; i < codeLength; i++) {
        let index = Math.floor(Math.random() * 36);  //取随机数 获取索引索引（0-35）
        code += random[index];
      }
      this.reOne = code[0];
      this.reTwo = code[1];
      this.reThree = code[2];
      this.reFour = code[3];
      //这里直接调用获取样式，保证验证码生成之后，可以直接获取验证码的样式
      this.getStyle();
      this.code = code;
      return code.toString();
    }
    ,
    getStyle() {
      let chars = [
        "font-weight: bold;font-size: 32px;text-decoration:overline;color:#BA55D3;font-family: 'Arabic Typesetting'",
        "font-weight: bold;font-size: 26px;text-decoration:overline;color:pink;font-family: 'PingFang SC'",
        "font-weight: solid;font-size: 33px;text-decoration:line-through;color:hotpink;font-family: Vijaya",
        "font-weight: bold;font-size: 25px;text-decoration:underline;color:#9400D3; font-family: Aharoni",
        "font-weight: bold;font-size: 29px;text-decoration:underline;color:#FF8C00;font-family: 'Arabic Typesetting'",
        "font-weight: bold;font-size: 27px;text-decoration:line-through;color:#FF1493;font-family: 'PingFang SC'",
        "font-weight: solid;font-size: 28px;text-decoration:line-through;color:#1E90FF;font-family: Vijaya",
      ];
      let res = [];
      for (let i = 0; i < 4; i++) {
        let id = Math.ceil(Math.random() * 7);
        res[i] += chars[id - 1];
      }
      this.reOneStyle = res[0];
      this.reTwoStyle = res[1];
      this.reThreeStyle = res[2];
      this.reFourStyle = res[3];
      return res.toString();
    }
    ,
    clickAuthImg() {
      this.createCode()
    },
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #fc4669, #3f53fb);
  overflow: hidden;
}

.login-code-img {
  background-image: url(../assets/loginBack.webp);
  float: right;
  width: 100px;
  margin-left: 35px;
  height: 40px;
  overflow: hidden;
  display: inline-block;
  color: #333;
  font-size: 14px;
  font-weight: 700;
  letter-spacing: 5px;
  line-height: 38px;
  text-indent: 5px;
  text-align: center;
}
</style>