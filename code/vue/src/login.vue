<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h2>登录</h2>
      </div>
      <div class="card_body">
        <el-form style="display: flex;flex-direction: column;" class="login_form" :model="loginForm" ref="loginForm"
                 :rules="loginRules">
          <el-form-item label="用户名" prop="username">
            <el-input placeholder="请输入用户名" v-model="loginForm.username" clearable
                      @keyup.enter="handleLogin"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input placeholder="请输入密码" show-password v-model="loginForm.password" clearable
                      @keyup.enter="handleLogin"></el-input>
          </el-form-item>
          <div style="display: flex;column-gap: 20px;align-items: center;">
            <el-form-item style="width: 200px;" label="验证码" prop="verifyCode">
              <el-input placeholder="请输入验证码" v-model="loginForm.verifyCode" clearable
                        @keyup.enter="handleLogin"></el-input>
            </el-form-item>
            <el-image @click="refreshVCImage" fit="fill" :src="verifyImage"
                      style="width: 120px;height: 70px;border: 1px solid black;cursor: pointer;"></el-image>
          </div>
          <el-row>
            <el-col :span="12">
              <el-form-item style="margin: 0;float: left;">
                <el-checkbox v-model="loginForm.rememberMe">记住我</el-checkbox>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item style="margin: 0;float: right;">
                <el-button style="margin-right: 10px;" type="text">忘记密码？</el-button>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button style="width: 150px;" type="primary" plain @click="handleLogin">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>
<script>
import {getVerifyImage, login} from "@/api/login";
import {setToken} from "@/utils/auth"
import {Base64} from "js-base64";

export default {
  name: "Login10",
  data() {
    return {
      loginForm: {
        username: null,
        verifyCode: null,
        password: null,
        rememberMe: false
      },
      loginRules: {
        username: [
          {required: true, message: "用户名不能为空", trigger: "blur"}
        ],
        password: [
          {required: true, message: "密码不能为空", trigger: "blur"}
        ],
        verifyCode: [
          {required: true, message: "验证码不能为空", trigger: "blur"}
        ]
      },
      verifyImage: "",
      uuid: null
    }
  },
  created() {
    this.refreshVCImage();
  },
  methods: {
    refreshVCImage() {
      getVerifyImage().then(response => {
        let data = response.data;
        this.uuid = data.uuid;
        this.verifyImage = data.image;
      })
    },
    handleLogin() {
      this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          login(this.loginForm.username,
              Base64.encode(this.loginForm.password),
              this.loginForm.verifyCode,
              this.uuid,
              this.loginForm.rememberMe).then(response => {
            if (response.code === 200) {
              setToken(response.token);
              this.$router.push({path: "/person-center", query: {username: this.loginForm.username}})
            }
          })
        }
      })
    }
  }
}
</script>
<style scoped>
.app-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  background: linear-gradient(110deg, #abdcff, #0396ff)
}

.box-card {
  width: 400px;
  height: 500px;
}
</style>