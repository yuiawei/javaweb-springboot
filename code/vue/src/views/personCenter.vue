<template>
  <div>
    <el-descriptions class="margin-top" title="个人中心" :column="2" border>
      <template slot="extra">
        <el-button type="danger" @click="userLogout">退出登录</el-button>
      </template>
      <el-descriptions-item label="ID">{{userInfo.id}}</el-descriptions-item>
      <el-descriptions-item label="用户名">{{userInfo.username}}</el-descriptions-item>
      <el-descriptions-item label="email">{{userInfo.email}}</el-descriptions-item>
      <el-descriptions-item label="手机号">{{userInfo.mobile}}</el-descriptions-item>
      <el-descriptions-item label="性别">{{userInfo.sex === "0" ? "男" : "女"}}</el-descriptions-item>
      <el-descriptions-item label="状态">{{userInfo.status}}</el-descriptions-item>
    </el-descriptions>
  </div>
</template>
<script>
import {getUserByUsername} from "@/api/user";
import {logout} from "@/api/login";
import {removeToken} from "@/utils/auth";

export default {
  name: "PersonCenter",
  data() {
    return {
      userInfo: {},
      username: null
    }
  },
  created() {
    this.username = this.$route.query.username;
    this.getUserInfo();
  },
  methods: {
    userLogout() {
      logout().then(response => {
        removeToken();
        this.getUserInfo();
      })
    },
    getUserInfo() {
      getUserByUsername(this.username).then(response => {
        this.userInfo = response.data;
      })
    }
  }
}
</script>

<style scoped>

</style>