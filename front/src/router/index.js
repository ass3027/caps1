import Vue from "vue";
import VueRouter from "vue-router";

//기본
import JoinView from "../views/auth/JoinView.vue";
import LoginView from "@/views/auth/LoginView";


//성호형??
import SuppliesVue from "../views/supply/SuppliesView";
import PlannerShareView from "../views/share/PlannerShareView";
import PlannerShareDetailsViewView from "../views/share/PlannerShareDetailsView";
import PlannerShareWriteView from "../views/share/PlannerShareWriteView";
import PlannerPicView from "@/views/share/PlannerPicView";
import PlannerShareEditView from "@/views/share/PlannerShareEditView";
import LocationCheckView from "@/views/LocationCheckView";
import OrderDetail from "@/components/order/OrderDetail";
import DuserOrdersComponent from "@/components/order/DuserOrdersComponent";
import placeSuppliesView from "@/views/supply/PlaceSuppliesView";

//가방 보관
import BagReserveView from '../views/bag/order/SelectionOrder.vue'
import TrackingView from "@/views/bag/TrackingView";
import testBshView from "@/views/supply/TestBshView";
import TestBshDetailView from "@/views/supply/TestBshDetailView";
import PlaceSuppliesView from "@/views/supply/PlaceSuppliesView";


//봉식

// import TrackingView from "@/views/bag/TrackingView";
import FareView from "../views/bag/FareView";
import UsageGuideView from "../views/bag/UsageGuideView";
import TransportOrder from "../views/bag/order/TransportOrder";
import SelectionOrder from "../views/bag/order/SelectionOrder";
import StorageOrder from "@/views/bag/order/StorageOrder"
import PlInviteView from "@/views/planner/PlInviteView";
import PlanView from "@/views/planner/PlanView";
import CalenderView from "@/views/planner/CalenderView";

//혁태??
import HotelView from "@/views/store/HotelView";
import StoreAdd from "@/views/store/StoreAdd";
import PensionView from "@/views/store/PensionView";
import StoreDetail from "@/views/store/StoreDetail";

//진한??
import GuideView from "@/views/guide/GuideView";
import GuideRegister from "@/views/guide/GuideRegister";
import GuideProductReg from "@/views/guide/GuideProductReg";
import GuideReserve from "@/views/guide/GuideReserve";
import GuideViewUser from "@/views/guide/GuideViewUser";
import GuideSearch from "@/views/guide/GuideSearch";
import GuideProduct from "@/views/guide/GuideProduct";
import GuideProductTime from "@/views/guide/GuideProductTime";
import GuideProductView from "@/views/guide/GuideProductView";
import GuideProductSearch from "@/views/guide/GuideProductSearch";



//세운??
import ReviewView from "@/views/ReviewView";
import StoreReviewView from "@/views/StoreReviewView";
import ReviewCreateView from "@/views/ReviewCreateView";
import LocationUpdate from "@/views/LocationUpdate";
//민아
import MypageView from "@/views/auth/MypageView";
import BookMarkView from "@/views/auth/BookMarkView";
import MyDataView from "@/views/auth/MyPageUpdateView";
import QuestionsView from "@/views/auth/QuestionsView";
import WritingView from "@/views/auth/WritingView";
import DetailPageView from "@/views/auth/DetailPageView";
import WritingModView from "@/views/auth/WritingModView";
import MINA from "@/views/MINA";
import TestView from "@/views/TestView";

import { EventBus} from "@/eventBus/eventBus";
import axios from 'axios'
import {store} from "@/store"
import GuideIntro from "@/views/guide/GuideIntro";
import GuideReserveInfo from "@/views/guide/GuideReserveInfo";

import MyPageUpdateView from "@/views/auth/MyPageUpdateView";
import ProductBook from "@/views/store/ProductBook";
import productBookView from "@/views/store/productBookView";



Vue.use(VueRouter);

const routes = [
  //민아
  {path: "/join", name: "join", component: JoinView},
  {path: "/login", name: "login", component: LoginView},
  {path: "/myPage", name: "MyPage", component: MypageView},
  {path: "/myPageUpdate", name: "MyPageUpdate", component: MyPageUpdateView},
  {path: "/questions", name: "Questions", component: QuestionsView},
  {path: "/writing", name: "Writing", component: WritingView},
  {path: "/detailPage/:id", name: "DetailPage", component: DetailPageView},
  {path: "/detailPage/:id/writingMod", name: "WritingMod", component: WritingModView,},
  {path: "/bookmark", name: "BookMark", component: BookMarkView},


  //성호
  {path: "/supplies", name: "supplies", component: SuppliesVue},
  {path: "/supplies", name: "supplies", component: SuppliesVue},
  {path: "/share", name: "share", component: PlannerShareView},
  {path: "/share/:id", name: "shareDetails", component: PlannerShareDetailsViewView,},
  {path: "/share/write", name: "shareWrite", component: PlannerShareWriteView,},
  {path: "/share/edit", name: "shareEdit", component: PlannerShareEditView, props: true,},
  {path: "/bsh", name: "bsh", component: testBshView},
  {path: "/bsh/:id", name: "bshDetail", component: TestBshDetailView,props:true},
  {path: "/placeSupplies", name: "placeSupplies", component: PlaceSuppliesView},

  //혁태
  {path: "/place/:category", name: "place", component: HotelView, props: true},
  {path: "/hotel", name: "hotel", component: HotelView},
  {path: "/palce/:category/:pl_id", name: "hotelDetail", component: StoreDetail, props: true,},
  {path: "/placeadd", name: "placeAdd", component: StoreAdd},
  {path: "/productBook", name: "productBook", component: ProductBook,props:true},
  {path: "/productBookView", name: productBookView, component: productBookView},
  // {path: '/motel', name: 'motel', component: MotelView,},\
  // {path: '/store', name: 'store', component: StoreView,}, //props: {value:String}},

  //세진
  {path: "/calender", name: "calender", component: CalenderView},
  {path: "/plInvite", name: "plInvite", component: PlInviteView},
  {path: "/plan", name: "plan", component: PlanView},
  {path: "/planPic", name: "planPic", component: PlannerPicView},

  //봉식
  {path: "/TransportOrder", name: TransportOrder, component: TransportOrder},
  {path: "/SelectionOrder", name: SelectionOrder, component: SelectionOrder},
  {path: "/StorageOrder", name: StorageOrder, component: StorageOrder},
  {path: "/FareView", name: "FareView", component: FareView},
  {path: "/UsageGuideView", name: "UsageGuideView", component: UsageGuideView,},
  {path: "/TransportOrder", name: "TransportOrder", component: TransportOrder},
  {path: "/TrackingView", name: "TrackingView", component: TrackingView},
  {path: "/StorageOrder", name: "StorageOrder", component: StorageOrder},

  // { path: "/OrderView", name: "OrderView", component: OrderView },


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
    path: "/ReviewView/:productId",
    name: "ReviewView",
    component: ReviewView,
    props: true,
  },
  {
    path: "/ReviewCreateView",
    name: "ReviewCreateView",
    component: ReviewCreateView,
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


  { path: "/duser/ orders", name: "DuserOrdersComponent", component: DuserOrdersComponent},
  { path: "/orderDetail/:ordId", name: "OrderDetail", component: OrderDetail, props: true },
  { path: "/TestView", name: "TestView", component: TestView },

  //진한
  {path: "/GuideView", name: "GuideView", component: GuideView},
  {path: "/GuideRegister", name: "GuideRegister", component: GuideRegister},
  {path: "/GuideProductReg", name: "GuideProductReg", component: GuideProductReg,},
  {path: "/GuideReserve", name: "GuideReserve", component: GuideReserve},
  {path: "/GuideView/:user_id", name: "GuideViewUser", component: GuideViewUser, props: true},
  {path: "/GuideView/Search/:keyword", name: "GuideSearch", component: GuideSearch, props: true},
  {path: "/GuideProduct", name: "GuideProduct", component: GuideProduct,props:true},
  {path: "/GuideProductTime/:gitem_id", name: "GuideProductTime", component: GuideProductTime, props: true},
  {path: "/GuideProduct/:gitem_id", name:"GuideProductView",component: GuideProductView, props:true},
  {path: "/GuideProduct/Search/:keyword", name:"GuideProductSearch",component: GuideProductSearch,props: true},
  {path: "/GuideIntro/:user_id", name:"GuideIntro", component: GuideIntro},
  {path: "/GuideReserveInfo", name:"GuideReserveInfo", component: GuideReserveInfo},
  //세운
  {path: "/location/check/:ord_id", name: "LocationCheckView", component: LocationCheckView, props: true,},
  {path: "/location/update/:duser_id", name: "LocationUpdateView", component: LocationUpdate, props: true,},
  {path: "/location/check/:ord_id", name: "LocationCheckView", component: LocationCheckView,},
  {path: "/ReviewView/:productId", name: "ReviewView", component: ReviewView, props: true,},
  {path: "/ReviewCreateView/:type/:id", name: "ReviewCreateView", component: ReviewCreateView, props: true,},
  {path: "/review/store/:store_name", name: "StoreReview", component: StoreReviewView, props: true,},
  {path: "/TestView/:ord_id", name: "TestView", component: LocationCheckView},

  //민아 실험?
  {path: "/mina", component: MINA}
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeResolve((to, from, next) => {
  if (to.path === "/login" || to.path === "/join" || to.path === "/") {
    next();
  } else {
    const dd = async () => {
      try {
        const id = await axios.get("/api/user/id");
        await store.dispatch("user/setUser", id.data);
        if ("anonymousUser" !== id.data) {
          console.log("login")
          const photo = await axios.get("/api/user/photo")
          EventBus.$emit("photoUpdate", photo.data)
          next()
        } else {
          console.log("not login")
          next('/login');
        }
      } catch (err) {
        console.log(err)
      }
    }
    dd()
  }
})
export default router;
