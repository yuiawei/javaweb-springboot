<template>
  <div class="container">
    <i class="left-btn" @click="prev"></i>
    <i class="right-btn" @click="next"></i>
    <div class="slider-list">
      <img v-for="img in imgArr" @click="clickImg" @mouseover="stopTimer" @mouseout="startTimer" class="img-item" :src="img.src" :key="img.id" :alt="img.msg"/>
    </div>
    <!--    <img class="img-item" v-for="(view, index) in viewArr" :src="view" :key="index" :alt="index"/>-->
  </div>
</template>
<script>
import lizicheng from "@/assets/image/lizicheng.png";
import bilibili from "@/assets/image/bilibili.png";

export default {
  name: "PhotoCarousel",
  props: {},
  data() {
    return {
      // 最多6张图片轮播
      imgArr: [
        {id: "001", src: lizicheng, msg: "李自成"},
        {id: "002", src: bilibili, msg: "李自成"},
        {id: "003", src: lizicheng, msg: "李自成"},
        {id: "004", src: bilibili, msg: "李自成"},
        {id: "005", src: lizicheng, msg: "李自成"},
        {id: "006", src: bilibili, msg: "李自成"},
      ],
      idArr: ["first", "second", "right", "left", "left", "last"],
      timer: null
    }
  },
  mounted() {
    this.initialize();
    this.timer = setInterval(this.next, 3000);
  },
  methods: {
    prev() {
      console.log("prev")
      this.idArr.push(this.idArr.shift());
      this.initialize();
    },
    next() {
      console.log("next")
      this.idArr.unshift(this.idArr.pop());
      this.initialize();
    },
    clickImg(val) {
      console.log(val)
    },
    initialize() {
      let img = document.querySelectorAll(".img-item");
      for (let i = 0; i < img.length; i++) {
        img[i].id = this.idArr[i];
      }
    },
    stopTimer() {
      clearInterval(this.timer);
      this.timer = null;
    },
    startTimer() {
      this.timer = setInterval(this.next, 3000);
    }
  }
}
</script>
<style scoped>
.container {
  width: 1080px;
  height: 300px;
  position: relative;
  overflow-x: hidden;
}

.slider-list {
  width: 1080px;
  height: 300px;
  position: relative;
}

.img-item {
  position: absolute;
  top: 0;
  left: 0;
  width: 750px;
  height: 300px;
  border-radius: 10px;
  z-index: -1;
  cursor: pointer;
  transition: 0.3s;
}

/* 1 被选中左侧 */
#last {
  transform: translateX(-49px) scale(0.87);
  z-index: 0;

}

/* 2 被选中 */
#first {
  transform: translateX(165px) scale(1);
  z-index: 1;
}

/* 3 被选中右侧 */
#second {
  transform: translateX(380px) scale(0.87);
  z-index: 0;
}

#left {
  transform: translateX(-100px) scale(0.87);
  opacity: 0;
  z-index: -1;
}

#right {
  transform: translateX(500px) scale(0.87);
  opacity: 0;
  z-index: -1;
}

.left-btn {
  display: block;
  width: 30px;
  height: 50px;
  background-image: url("@/assets/image/left.png");
  z-index: 1;
  position: absolute;
  top: 43%;
  left: 0;
  cursor: pointer;
}

.right-btn {
  display: block;
  width: 30px;
  height: 50px;
  background-image: url("@/assets/image/right.png");
  z-index: 1;
  position: absolute;
  top: 43%;
  right: 0;
  cursor: pointer;
}
</style>