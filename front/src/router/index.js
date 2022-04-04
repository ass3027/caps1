import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import JoinView from '../views/JoinView.vue'

import OrderView from '../views/bag/order/OrderView.vue'
import FareView from '../views/bag/FareView'
import ReviewView from '../views/bag/ReviewView'
import UsageGuideView from '../views/bag/UsageGuideView'
import TransportView from '../views/bag/order/TransportView'
import TrackingView from "@/views/bag/order/TrackingView";

import PlInviteView from "@/views/planner/PlInviteView";
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
    path: '/map',
    name: 'map',
    component:MapView
  },


    //가방페이지
  {
    path: '/OrderView',
    name: 'OrderView',
    component: OrderView
  },
  {
    path: '/FareView',
    name: 'FareView',
    component: FareView
  },
  {
    path: '/ReviewView',
    name: 'ReviewView',
    component:ReviewView
  },
  {
    path: '/UsageGuideView',
    name: 'UsageGuideView',
    component: UsageGuideView
  },
  {
    path: '/TransportView',
    name: 'TransportView',
    component: TransportView
  },
  {
    path: '/TrackingView',
    name: 'TrackingView',
    component: TrackingView
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
