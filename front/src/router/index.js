import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import JoinView from '../views/JoinView.vue'
import MapView from "@/views/MapView"

import OrderView from '../views/bag/OrderView'
import FareView from '../views/bag/FareView'
import ReviewView from '../views/bag/ReviewView'
import UsageGuideView from '../views/bag/UsageGuideView'
import TransportView from '../views/bag/TransportView'

import PlInviteView from "@/views/planner/PlInviteView";
import IndexView from '../views/bag/IndexView'
import MapView from "@/views/planner/MapView";
import SignView from "@/views/sign.vue"
import PlanView from "@/views/planner/PlanView";

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


    //가방페이지
  {
    path: '/OrderView',
    name: '가방예약',
    component: OrderView
  },
  {
    path: '/FareView',
    name: '이용안내',
    component: FareView
  },
  {
    path: '/ReviewView',
    name: '요금',
    component:ReviewView
  },
  {
    path: '/UsageGuideView',
    name: '후기',
    component: UsageGuideView
  },
  {
    path: '/TransportView',
    name: '운송조회',
    component: TransportView
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
