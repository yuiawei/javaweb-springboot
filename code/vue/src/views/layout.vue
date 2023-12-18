<template>
  <div class="app-container">
    <Header/>
    <router-view/>
    <Footer/>
  </div>
</template>
<script>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "Layout",
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
</style>