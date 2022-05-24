import Vue from "vue";
import VueRouter from "vue-router";

import JoinView from "../views/auth/JoinView.vue";
import LoginView from "@/views/auth/LoginView";

import SuppliesVue from "../views/supply/SuppliesView";
import PlannerShareView from "../views/share/PlannerShareView";
import PlannerShareDetailsViewView from "../views/share/PlannerShareDetailsView";
import PlannerShareWriteView from "../views/share/PlannerShareWriteView";
import PlannerPicView from "@/views/share/PlannerPicView";
import PlannerShareEditView from "@/views/share/PlannerShareEditView";
import LocationCheckView from "@/views/LocationCheckView";

//가방 보관
import BagReserveView from "../views/bag/order/BagReserveView.vue";
import TrackingView from "@/views/bag/TrackingView";
import StorageView from "@/views/bag/order/StorageView";

import FareView from "../views/bag/FareView";
import UsageGuideView from "../views/bag/UsageGuideView";
import TransportView from "../views/bag/order/TransportView";

import StoreReviewView from "@/views/StoreReviewView";

import PlInviteView from "@/views/planner/PlInviteView";
import PlanView from "@/views/planner/PlanView";
import CalenderView from "@/views/planner/CalenderView";

import HotelView from "@/views/store/HotelView";
import StoreAdd from "@/views/store/StoreAdd";

import PensionView from "@/views/pension/PensionView";

import GuideView from "@/views/guide/GuideView";
import GuideRegister from "@/views/guide/GuideRegister";
import GuideProductReg from "@/views/guide/GuideProductReg";
import GuideReserve from "@/views/guide/GuideReserve";
import GuideViewUser from "@/views/guide/GuideViewUser";

import ProductReviewView from "@/views/ProductReviewView";
import ReviewCreateView from "@/views/ReviewCreateView";
import StoreReviewView from "@/views/StoreReviewView";

import ReviewCreateView from "@/views/ReviewCreateView";
import MypageView from "@/views/auth/MypageView";

import StoreDetail from "@/views/store/StoreDetail";
import GuideSearch from "@/views/guide/GuideSearch";
import GuideProduct from "@/views/guide/GuideProduct";

import LocationUpdate from "@/views/LocationUpdate";
import BookMarkView from "@/views/auth/BookMarkView";
import MyDataView from "@/views/auth/MyDataView";
import QuestionsView from "@/views/auth/QuestionsView";
import WritingView from "@/views/auth/WritingView";
import DetailPageView from "@/views/auth/DetailPageView";
import WritingModView from "@/views/auth/WritingModView";

import {store} from "@/store"
import axios from "axios";
import {EventBus} from "@/eventBus/eventBus";

Vue.use(VueRouter);

const routes = [
  { path: "/join", name: "join", component: JoinView },
  { path: "/login", name: "login", component: LoginView },
  { path: "/myPage", name: "MyPage", component: MypageView },
  { path: "/myData", name: "MyData", component: MyDataView },
  { path: "/questions", name: "Questions", component: QuestionsView },
  { path: "/writing", name: "Writing", component: WritingView },
  { path: "/detailPage/:id", name: "DetailPage", component: DetailPageView },
  {
    path: "/detailPage/:id/writingMod",
    name: "WritingMod",
    component: WritingModView,
  },
  { path: "/bookmark", name: "Bookmark", component: BookMarkView },
  { path: "/supplies", name: "supplies", component: SuppliesVue },

  { path: "/supplies", name: "supplies", component: SuppliesVue },
  { path: "/share", name: "share", component: PlannerShareView },
  {
    path: "/share/:id",
    name: "shareDetails",
    component: PlannerShareDetailsViewView,
  },
  {
    path: "/share/write",
    name: "shareWrite",
    component: PlannerShareWriteView,
  },
  {
    path: "/share/edit",
    name: "shareEdit",
    component: PlannerShareEditView,
    props: true,
  },

  { path: "/hotel", name: "hotel", component: HotelView },
  {
    path: "/hotel/:store_id",
    name: "hotel",
    component: StoreDetail,
    props: true,
  },
  { path: "/storeadd", name: "storeadd", component: StoreAdd },
  // {path: '/motel', name: 'motel', component: MotelView,},\
  // {path: '/store', name: 'store', component: StoreView,}, //props: {value:String}},

  { path: "/pension", name: "pension", component: PensionView },

  { path: "/calender", name: "calender", component: CalenderView },
  { path: "/BagReserveView", name: "OrderView", component: BagReserveView },
  { path: "/FareView", name: "FareView", component: FareView },
  {
    path: "/UsageGuideView",
    name: "UsageGuideView",
    component: UsageGuideView,
  },
  { path: "/TransportView", name: "TransportView", component: TransportView },
  { path: "/TrackingView", name: "TrackingView", component: TrackingView },
  { path: "/StorageView", name: "StorageView", component: StorageView },

  // { path: "/OrderView", name: "OrderView", component: OrderView },
  { path: "/plInvite", name: "plInvite", component: PlInviteView },
  { path: "/plan", name: "plan", component: PlanView },
  { path: "/planPic", name: "planPic", component: PlannerPicView },

  { path: "/GuideView", name: "GuideView", component: GuideView },
  { path: "/GuideRegister", name: "GuideRegister", component: GuideRegister },
  {
    path: "/GuideProductReg",
    name: "GuideProductReg",
    component: GuideProductReg,
  },
  { path: "/GuideReserve", name: "GuideReserve", component: GuideReserve },
  {
    path: "/GuideView/:user_id",
    name: "GuideViewUser",
    component: GuideViewUser,
    props: true,
  },
  {
    path: "/GuideView/Search/:keyword",
    name: "GuideSearch",
    component: GuideSearch,
    props: true,
  },
  { path: "/GuideProduct", name: "GuideProduct", component: GuideProduct },

  {
    path: "/ProductReviewView/:productId",
    name: "ProductReviewView",
    component: ProductReviewView,
    props: true,
  },
  {
    path: "/ReviewCreate/:pay_id",
    name: "ReviewCreate",
    component: ReviewCreateView,
    props: true,
  },
  {
    path: "/review/store/:store_name",
    name: "StoreReview",
    component: StoreReviewView,
    props: true,
  },

  {
    path: "/location/check/:ord_id",
    name: "LocationCheckView",
    component: LocationCheckView,
    props: true,
  },
  {
    path: "/location/update/:duser_id",
    name: "LocationUpdateView",
    component: LocationUpdate,
    props: true,
  },

  {
    path: "/location/check/:ord_id",
    name: "LocationCheckView",
    component: LocationCheckView,
  },

  { path: "/TestView/:ord_id", name: "TestView", component: LocationCheckView },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeResolve((to, from, next) => {
  if (to.path === "/login" || to.path === "/join" || to.path === "/") {
    next();
  }else  {
    const dd = async ()=> {
      try {
        const id = await axios.get("/api/user/id");
        await store.dispatch("user/setUser",id.data);
        if("anonymousUser" !== id.data) {
          console.log("login")
          const photo = await axios.get("/api/user/photo")
          EventBus.$emit("photoUpdate",photo.data)
          next()
        } else {
          console.log("not login")
          next('/login');
        }
      }
      catch(err) {
        console.log(err)
      }
    }
    dd()
  }

export default router;
