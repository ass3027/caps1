import Vue from 'vue'
import VueRouter from 'vue-router'

import HomeView from '../views/HomeView.vue'
import JoinView from '../views/JoinView.vue'

import IndexView from '../views/bag/IndexView'
import MapView from '../views/MapView';

import AboutView from '../views/AboutView';

Vue.use(VueRouter)

const routes = [
  { path: '/',      name: 'home',   component: HomeView },
  { path: '/about', name: 'about',  component: AboutView },
  { path: '/join',  name: 'join',   component:JoinView },
  { path: '/bag',   name: 'bag',    component: IndexView },
  { path: '/map',   name: 'map',    component:MapView },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
