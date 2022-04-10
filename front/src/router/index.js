import Vue from "vue";
import VueRouter from "vue-router";

import JoinView from "../views/auth/JoinView.vue";
import LoginView from "@/views/auth/LoginView";

import ImportSuppliesView from "@/views/ImportSuppliesView";
import SuppliesVue from '../views/SuppliesView';
import PlannerShareView from '../views/share/PlannerShareView'
import PlannerShareDetailsViewView from '../views/share/PlannerShareDetailsView'
import PlannerShareWriteView from '../views/share/PlannerShareWriteView'
import PlannerPicView from "@/views/share/PlannerPicView";

//가방 보관
import BagReserveView from '../views/bag/order/BagReserveView.vue'
import ReviewView from '../views/bag/ReviewView'
import TrackingView from "@/views/bag/TrackingView";
import StorageView from "@/views/bag/order/StorageView";

import FareView from "../views/bag/FareView";
import UsageGuideView from "../views/bag/UsageGuideView";
import TransportView from "../views/bag/order/TransportView";

import PlInviteView from "@/views/planner/PlInviteView";
import PlanView from "@/views/planner/PlanView";
import CalenderView from "@/views/planner/CalenderView";

import HotelView from "@/views/travel/HotelView";
import HotelAdd from "@/views/travel/HotelAdd";


import GuideView from "@/views/guide/GuideView";
import GuideRegister from "@/views/guide/GuideRegister";
import GuideProductReg from "@/views/guide/GuideProductReg";
import GuideReserve from "@/views/guide/GuideReserve";
import GuideViewUser from "@/views/guide/GuideViewUser";


import Hoteltest from "@/views/travel/HotelTest";


Vue.use(VueRouter);

const routes = [
  {path: '/join', name: 'join', component: JoinView},
  {path: "/login", name: "login", component: LoginView},
  {path: '/supplies', name: 'supplies', component: SuppliesVue},
  {path: '/supplies/sets', name: 'ImportSupplies', component: ImportSuppliesView},
  {path: '/share', name: 'share', component: PlannerShareView},
  {path: '/share/:id', name: 'shareDetails', component: PlannerShareDetailsViewView},
  {path: '/share/write', name: 'shareWrite', component: PlannerShareWriteView},
  {path: '/hotel', name: 'hotel', component: HotelView,},
  {path: '/hoteladd', name: 'hoteladd', component: HotelAdd,},
  {path: '/calender', name: 'calender', component: CalenderView},
  {path: '/BagReserveView', name: 'OrderView', component: BagReserveView},
  {path: '/FareView', name: 'FareView', component: FareView},
  {path: '/ReviewView', name: 'ReviewView', component: ReviewView},
  {path: '/UsageGuideView', name: 'UsageGuideView', component: UsageGuideView},
  {path: '/TransportView', name: 'TransportView', component: TransportView},
  {path: '/TrackingView', name: 'TrackingView', component: TrackingView},
  {path: '/StorageView', name: 'StorageView', component: StorageView},
  { path: "/hoteltest", name: "hoteltest", component: Hoteltest },
 // { path: "/OrderView", name: "OrderView", component: OrderView },
  {path: '/plInvite', name: 'plInvite', component: PlInviteView},
  {path: '/plan', name: 'plan', component: PlanView},
  {path: '/planPic', name: 'planPic', component: PlannerPicView},

  {path: "/GuideView", name: "GuideView", component: GuideView},
  {path: "/GuideRegister", name: "GuideRegister", component: GuideRegister},
  {path: "/GuideProductReg", name: "GuideProductReg", component: GuideProductReg,},
  {path: "/GuideReserve", name: "GuideReserve", component: GuideReserve},
  {path: "/GuideView/:user_id", name: "GuideViewUser", component: GuideViewUser, props:true}
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
