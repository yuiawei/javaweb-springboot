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
      path: "/login9",
      name: "login9",
      component: () => import("@/login9.vue")
    },
    {
      path: "/login10",
      name: "login10",
      component: () => import("@/login10.vue")
    },
    {
      path: "/person-center",
      name: "personCenter",
      component: () => import("@/views/personCenter.vue")
    },
	{
	  path: "/week10",
	  name: "week10",
	  component: () => import("@/views/week10.vue")
	},
  ]
})

export default router
