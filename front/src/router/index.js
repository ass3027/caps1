import Vue from 'vue';
import VueRouter from 'vue-router';

import HomeView from '../views/HomeView.vue';
import JoinView from '../views/JoinView.vue';

import IndexView from '../views/bag/IndexView';
import MapView from '../views/MapView';
import ImportSuppliesView from "@/views/ImportSuppliesView";

import SuppliesVue from '../views/SuppliesView';

import OrderView from '../views/bag/order/OrderView.vue'
import FareView from '../views/bag/FareView'
import ReviewView from '../views/bag/ReviewView'
import UsageGuideView from '../views/bag/UsageGuideView'
import TransportView from '../views/bag/order/TransportView'

import PlInviteView from "@/views/planner/PlInviteView";
import SignView from "@/views/sign.vue"
import PlanView from "@/views/planner/PlanView";
import CalenderView from "@/views/planner/CalenderView";
import AboutView from '../views/AboutView';

import HotelView from '@/views/travel/HotelView';
import HotelAdd from '@/views/travel/HotelAdd';
import GuideView from '@/views/guide/GuideView';
import GuideRegister from "@/views/guide/GuideRegister";
import GuideProductReg from "@/views/guide/GuideProductReg";
import GuideReserve from "@/views/guide/GuideReserve";

Vue.use(VueRouter);

const routes = [

	{ path: '/', name: 'home', component: HomeView },
	{ path: '/about', name: 'about', component: AboutView },
	{ path: '/join', name: 'join', component: JoinView },
	{ path: '/bag', name: 'bag', component: IndexView },
	{ path: '/map', name: 'map', component: MapView },
	{ path: '/supplies', name: 'supplies', component: SuppliesVue },
	{ path: '/supplies/sets', name: 'ImportSupplies', component:ImportSuppliesView },
  { path: '/hotel', name: 'hotel', component: HotelView,},
	{ path: '/hoteladd', name: 'hoteladd', component: HotelAdd,},
   { path: '/calender', name: 'calender', component:CalenderView},
   { path: '/OrderView', name: 'OrderView', component: OrderView},
   { path: '/FareView', name: 'FareView',component: FareView},
   { path: '/ReviewView', name: 'ReviewView', component:ReviewView},
   { path: '/UsageGuideView', name: 'UsageGuideView', component: UsageGuideView},
   { path: '/TransportView', name     : 'TransportView', component: TransportView},
   { path: '/invite', name: 'invite', component:PlInviteView},
   { path:'/plan', name:'plan', component: PlanView},
   { path: '/sign', name: 'sign', component:SignView},
	{ path: '/GuideView', name: 'GuideView', component:GuideView},
	{ path: '/GuideRegister', name: 'GuideRegister', component:GuideRegister},
	{ path: '/GuideProductReg', name: 'GuideProductReg', component:GuideProductReg},
	{ path: '/GuideReserve', name: 'GuideReserve', component:GuideReserve},


];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
