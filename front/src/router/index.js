import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import JoinView from '../views/JoinView.vue'
import PlInviteView from "@/views/PlInviteView";
import IndexView from '../views/bag/IndexView'
import MapView from "@/views/MapView";
import SignView from "@/views/sign.vue"
import PlanView from "@/views/PlanView";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',

    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/join',
    name: 'join',
    component:JoinView
  },
  {
    path: '/bag',
    name: 'bag',
    component: IndexView
  },
  {
    path: '/map',
    name: 'map',
    component:MapView
  },
  {
    path: '/map',
    name: 'map',
    component:MapView
  },
  {
    path: '/invite',
    name: 'invite',
    component:PlInviteView
  },
  {
    path:'/plan',
    name:'plan',
    component: PlanView
  },



  {
    path: '/sign',
    name: 'sign',
    component:SignView
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
