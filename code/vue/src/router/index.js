import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'hash',
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: "/",
      redirect: '/index'
    },
    {
      path: "/index",
      name: "index",
      component: () => import("@/index.vue")
    },
    {
      path: "/login",
      name: "login",
      component: () => import("@/login.vue")
    },
    {
      path: "/person-center",
      name: "personCenter",
      component: () => import("@/views/personCenter.vue")
    }
  ]
})

export default router
