<template>
  <header>
    <div>
      <el-image style="width: 60px; height: 60px;margin: 0 10px" :src="require('@/assets/image/eb.png')" fit="fill"></el-image>
      <el-menu style="display: inline-block" :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1">首页</el-menu-item>
        <el-menu-item index="2">求职</el-menu-item>
        <el-menu-item index="3">简历</el-menu-item>
      </el-menu>
    </div>
    <div class="person">
      <el-button style="margin: 10px" type="primary" @click="toLogin">登录/注册</el-button>
    </div>
  </header>
</template>
<script>

export default {
  name: "Header",
  data() {
    return {
      activeIndex: localStorage.getItem("activeIndex") ? localStorage.getItem("activeIndex") : "1"
    }
  },
  methods: {
    toLogin() {
      this.$router.push("/login");
    },
    handleSelect(key, keyPath) {
      let path = this.$route.path;
      if (key === "1") {
        if (path !== "/index") {
          localStorage.setItem("activeIndex", "1");
          this.$router.push("/index");
        }
      } else if (key === "2") {
        if (path !== "/job-hunt") {
          localStorage.setItem("activeIndex", "2");
          this.$router.push("/job-hunt");
        }
      } else {
        if (path !== "/resume") {
          localStorage.setItem("activeIndex", "3");
          this.$router.push("/resume");
        }
      }
    }
  }
}
</script>
<style scoped>
header {
  position: sticky;
  top: 0;
  height: 60px;
  background-color: #fff;
  z-index: 100;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.person {
  height: 60px;
  position: relative;
  z-index: 2;
}

.el-menu.el-menu--horizontal {
  border-bottom: none !important;
}
</style>