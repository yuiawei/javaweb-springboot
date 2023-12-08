<template>
  <header>
    <div>
      <h1 class="title">linxi blog</h1>
    </div>
    <div class="person">
<!--      <img :src="require('@/assets/image/bilibili.png')" alt="avatar"/>-->
      <el-button style="margin: 10px" type="primary" @click="toLogin">登录</el-button>
    </div>
  </header>
</template>
<script>
export default {
  name: "Header",
  methods: {
    toLogin() {
      this.$router.push("/login");
    }
  }
}
</script>
<style scoped>
header {
  position: sticky;
  top: 0;
  height: 60px;
  box-shadow: 0 2px 2px 0 rgba(0,0,0,.1);
  background-color: #fff;
  z-index: 100;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.title {
  font: 30px/60px Consolas,Sans-serif;
}

.person {
  width: 80px;
  height: 60px;
  position: relative;
  z-index: 2;
}

.person>img {
  margin: 5px 0;
  height: 50px;
  border-radius: 50%;
  box-shadow: 0 2px 5px #8c939d;
  cursor: pointer;
  transition: 0.3s;

  &:hover {
    transform: translateY(30px) scale(1.8);
  }
}
</style>