<template>
  <div class="app-container">
    <Header/>
    <main>
      <div class="article" v-for="art in 10">
        <div class="article-title">131415926米好131415926米好131415926米好131415926米好131415926米好131415926米好</div>
        <div class="article-info">
          <span class="time">
            发布时间：2023-10-10
          </span>|
          <span class="page-view">
            浏览量：20
          </span>|
          <span class="author">
            作者：curl
          </span>
        </div>
        <div class="article-content">131415926米好131415926米好131415926米好131415926米好131415926米好131415926米好131415926米好131415926米好131415926米好131415926米好131415926米好131415926米好</div>
        <div class="article-tags">
          标签：
          <span class="tag">Java</span>
          <span class="tag">MySQL</span>
        </div>
      </div>
    </main>
    <Footer/>
  </div>
</template>
<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "Index",
  components: {Footer, Header},
  data() {
    return {}
  },
  methods: {}
}
</script>
<style scoped>
.app-container {
  position: relative;
}

main {
  width: 1080px;
  margin: 20px auto 60px;
  min-height: 260px;
}

.article {
  width: 100%;
  height: 260px;
  box-shadow: 0 0 5px #999;
  border-radius: 5px;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
}

.article-title {
  height: 60px;
  background: linear-gradient(135deg,#abdcff,#0396ff);
  color: #fff;
  font: 36px/60px Consolas,Sans-serif;
  text-align: center;
  text-overflow: ellipsis;
}

.article-info {
  text-align: center;
  height: 40px;
  line-height: 40px;
}

.article-content {
  height: 100px;
  color: #aaabb0;
  font-family: Consolas, sans-serif;
  font-size: 20px;
  line-height: 20px;
  font-weight: bold;
  letter-spacing: 2px;
}

.article-tags {
  height: 60px;
}

.tag {
  display: inline-block;
  width: 80px;
  height: 30px;
  border: 1px solid black;
}
</style>