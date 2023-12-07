<template>
<div class="app-container">
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>JWT信息</span>
      <el-button style="float: right;" type="danger" @click="clearToken">清除token</el-button>
    </div>
    <div class="text item">
      <el-descriptions class="margin-top" direction="vertical" :column="3" border>
        <el-descriptions-item label="Header">{{personInfo.header}}</el-descriptions-item>
        <el-descriptions-item label="Payload">{{personInfo.payload}}</el-descriptions-item>
        <el-descriptions-item label="Signature">{{personInfo.signature}}</el-descriptions-item>
      </el-descriptions>
    </div>
  </el-card>
</div>
</template>
<script>
import {getPersonInfo} from "@/api/person";
import {removeToken} from "@/utils/auth";

export default {
  name: "PersonCenter",
  data() {
    return {
      username: "",
      personInfo: {}
    }
  },
  created() {
    this.username = this.$route.query.username;
    this.getPerson();
  },
  methods: {
    getPerson() {
      getPersonInfo(this.username).then(response => {
        this.personInfo = response;
      })
    },
    clearToken() {
      removeToken();
      this.getPerson();
    }
  }
}
</script>
<style scoped>

</style>