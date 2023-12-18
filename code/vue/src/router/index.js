import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/views/layout.vue";

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'hash',
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: "/login",
      name: "login",
      component: () => import("@/login.vue")
    },
    {
      path: "",
      component: Layout,
      redirect: 'index',
      children: [
        {
          path: "index",
          name: "Index",
          component: () => import("@/index.vue")
        },
        {
          path: "job-hunt",
          name: "JobHunt",
          component: () => import("@/views/jobHunt.vue")
        },
        {
          path: "resume",
          name: "Resume",
          component: () => import("@/views/Resume.vue")
        }
      ]

    },
    {
      path: "/person-center",
      name: "personCenter",
      component: () => import("@/views/personCenter.vue")
    }
  ]
})

export default router
