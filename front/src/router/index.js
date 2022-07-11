import Vue from "vue";
import VueRouter from "vue-router";


//성호형??
import SuppliesVue from "../views/supply/SuppliesView";
import PlannerShareView from "../views/share/PlannerShareView";
import PlannerShareDetailsViewView from "../views/share/PlannerShareDetailsView";
import PlannerShareWriteView from "../views/share/PlannerShareWriteView";
import PlannerPicView from "@/views/share/PlannerPicView";
import PlannerShareEditView from "@/views/share/PlannerShareEditView";
import LocationCheckView from "@/views/LocationCheckView";
import OrderDetail from "@/components/duser/OrderDetail";
import DuserOrders from "@/views/duser/DuserOrders";
import PlaceSuppliesView from "@/views/supply/PlaceSuppliesView";
import mainView from "@/views/MainView";
import TourDetailView from "@/views/TourDetailView";

//가방 보관
import testBshView from "@/views/supply/TestBshView";
import TestBshDetailView from "@/views/supply/TestBshDetailView";

//봉식
//--------------------------------------------------------------------
import GOrderDetail from "@/views/bag/general/GOrderDetail";
import GTrackingView from "@/views/bag/general/GTrackingView";
import FareView from "../views/bag/FareView";
import UsageGuideView from "../views/bag/UsageGuideView";
import TransportOrder from "../views/bag/order/TransportOrder";
import SelectionOrder from "../views/bag/order/SelectionOrder";
import StorageOrder from "@/views/bag/order/StorageOrder"
import PlInviteView from "@/views/planner/PlInviteView";
import PlanView from "@/views/planner/PlanView";
import CalenderView from "@/views/planner/CalenderView";
import KeeperTrackingView from "@/views/bag/keeper/KeeperTrackingView";
import KeeperOrderDetail from "@/views/bag/keeper/KeeperOrderDetail";
//--------------------------------------------------------------------
//혁태??
import HotelView from "@/views/store/HotelView";
import PlaceAdd from "@/views/store/PlaceAdd";
import StoreDetail from "@/views/store/StoreDetail";

//진한??
import GuideView from "@/views/guide/GuideView";
import GuideRegister from "@/views/guide/GuideRegister";
import GuideProductReg from "@/views/guide/GuideProductReg";
import GuideViewUser from "@/views/guide/GuideViewUser";
import GuideSearch from "@/views/guide/GuideSearch";
import GuideProduct from "@/views/guide/GuideProduct";

import GuideProductView from "@/views/guide/GuideProductView";
import GuideProductSearch from "@/views/guide/GuideProductSearch";


//세운??
import ReviewView from "@/views/ReviewView";
import StoreReviewView from "@/views/StoreReviewView";
import ReviewCreateView from "@/views/ReviewCreateView";
import LocationUpdate from "@/views/LocationUpdate";
//민아
import MypageView from "@/views/auth/MypageView";
import MyBookMark from "@/components/auth/MyBookMark";
import Questions from "@/components/auth/Questions";
import WritingView from "@/views/auth/WritingView";
import DetailPageView from "@/views/auth/DetailPageView";
import WritingModView from "@/views/auth/WritingModView";
import TestView from "@/views/TestView";

import {EventBus} from "@/eventBus/eventBus";
import axios from 'axios'
import {store} from "@/store"
import GuideIntro from "@/views/guide/GuideIntro";
import GuideReserveInfo from "@/views/guide/GuideReserveInfo";

import MyPageUpdate from "@/components/auth/MyPageUpdate";
import MyReview from "@/components/auth/MyReview";
import ProductBook from "@/views/store/ProductBook";
import productBookView from "@/views/store/productBookView";
import GuideReserve from "@/views/guide/GuideReserve";
import GuideProductTime from "@/views/guide/GuideProductTime";
import UserJoinView from "@/views/auth/UserJoinView";
import DeliveryJoinView from "@/views/auth/DeliveryJoinView";
import LoginView from "@/views/auth/LoginView";
import joinView from "@/views/auth/JoinView";
import KeeperJoinView from "@/views/auth/KeeperJoinView";
import AreaChartView from "@/views/auth/AreaChartView";
import AgeChartView from "@/views/auth/AgeChartView";
import chartView from "@/views/store/chartView";


Vue.use(VueRouter);

const routes = [
  //민아
  {path: "/join", name: "join", component: joinView}, //회원선택하UserJoinView는 창
  {path: "/userJoin", name: "userJoin", component: UserJoinView}, //일반회원 가입창
  {path: "/deliveryJoin", name: "DeliveryJoin", component: DeliveryJoinView}, //운송원회원 창
  {path: "/keeperJoin", name: "KeeperJoin", component: KeeperJoinView}, //운송원회원 창
  {path: "/login", name: "login", component: LoginView},
  {path: "/myPage/:select", name: "MyPage", component: MypageView, props: true},
  {path: "/myPage", name: "MyPage", component: MypageView},
  {path: "/myPageUpdate", name: "MyPageUpdate", component: MyPageUpdate},
  {path: "/questions", name: "Questions", component: Questions},
  {path: "/writing", name: "Writing", component: WritingView},
  {path: "/MyReview", name: "MyReview", component: MyReview},
  {path: "/detailPage/:id", name: "DetailPage", component: DetailPageView},
  {path: "/detailPage/:id/writingMod", name: "WritingMod", component: WritingModView,},
  {path: "/bookmark", name: "BookMark", component: MyBookMark},
  {path: "/ageChart", name: "AgeChart", component: AgeChartView},
  {path: "/areaChart", name: "AreaChart", component: AreaChartView},


  //성호
  {path: "/supplies", name: "supplies", component: SuppliesVue},
  {path: "/share", name: "share", component: PlannerShareView},
  {path: "/share/:id", name: "shareDetails", component: PlannerShareDetailsViewView,},
  {path: "/share/write", name: "shareWrite", component: PlannerShareWriteView,},
  {path: "/share/edit", name: "shareEdit", component: PlannerShareEditView, props: true,},
  {path: "/bsh", name: "bsh", component: testBshView},
  {path: "/bsh/:id", name: "bshDetail", component: TestBshDetailView, props: true},
  {path: "/placeSupplies", name: "placeSupplies", component: PlaceSuppliesView},
  {path: "/", name: "main", component: mainView},
  {path: "/tour/:pl_id", name: "tourDetail", component: TourDetailView, props: true},


  //혁태
  {path: "/place/:category", name: "place", component: HotelView, props: true},
  {path: "/hotel", name: "hotel", component: HotelView},
  {path: "/palce/:category/:pl_id", name: "hotelDetail", component: StoreDetail, props: true,},
  {path: "/placeAdd", name: "placeAdd", component: PlaceAdd},
  {path: "/productBook/:pd_id", name: "productBook", component: ProductBook, props: true},
  {path: "/productBookView", name: productBookView, component: productBookView},
  {path: "/chart", name: chartView, component: chartView},

  //세진
  {path: "/calender", name: "calender", component: CalenderView, props: true},
  {path: "/plInvite", name: "plInvite", component: PlInviteView},
  {path: "/plan", name: "plan", component: PlanView},
  {path: "/planPic", name: "planPic", component: PlannerPicView},

  //봉식
  //---------------------------------------------------------------//
  {path: "/GOrderDetail/:ordId", name: "GOrderDetail", component: GOrderDetail, props: true},
  {path: "/GTrackingView", name: "GTrackingView", component: GTrackingView, props: true},
  {path: "/FareView", name: "FareView", component: FareView},
  {path: "/UsageGuideView", name: "UsageGuideView", component: UsageGuideView,},
  {path: "/TransportOrder", name: "TransportOrder", component: TransportOrder},
  {path: "/SelectionOrder", name: "SelectionOrder", component: SelectionOrder},
  {path: "/StorageOrder", name: "StorageOrder", component: StorageOrder},
  {path: "/KeeperTrackingView", name: "KeeperTrackingView", component: KeeperTrackingView, props: true},
  {path: "/KeeperOrderDetail/:ord_id", name: "KeeperOrderDetail", component: KeeperOrderDetail, props: true},
  //---------------------------------------------------------------//


  {path: "/DuserOrders", name: "DuserOrders", component: DuserOrders},
  {path: "/orderDetail/:ordId", name: "OrderDetail", component: OrderDetail, props: true},
  {path: "/TestView", name: "TestView", component: TestView},

  //진한
  {path: "/GuideView", name: "GuideView", component: GuideView},
  {path: "/GuideRegister", name: "GuideRegister", component: GuideRegister},
  {path: "/GuideProductReg", name: "GuideProductReg", component: GuideProductReg,},
  {path: "/GuideView/:user_id", name: "GuideViewUser", component: GuideViewUser, props: true},
  {path: "/GuideView/Search/:keyword", name: "GuideSearch", component: GuideSearch, props: true},
  {path: "/GuideProduct", name: "GuideProduct", component: GuideProduct, props: true},
  {path: "/GuideProductTime/:gitem_id", name: "GuideProductTime", component: GuideProductTime, props: true},
  {path: "/GuideProduct/:gitem_id", name: "GuideProductView", component: GuideProductView, props: true},
  {path: "/GuideProduct/Search/:keyword", name: "GuideProductSearch", component: GuideProductSearch, props: true},
  {path: "/GuideIntro/:user_id", name: "GuideIntro", component: GuideIntro},
  {path: "/GuideReserveInfo", name: "GuideReserveInfo", component: GuideReserveInfo},
  {path: "/GuideReserve", name: "GuideReserve", component: GuideReserve},
  //세운
  {path: "/location/check/:ord_id", name: "LocationCheckView", component: LocationCheckView, props: true,},
  {path: "/location/update/:duser_id", name: "LocationUpdateView", component: LocationUpdate, props: true,},
  {path: "/ReviewView/:productId", name: "ReviewView", component: ReviewView, props: true,},
  {path: "/ReviewCreateView/:type/:id", name: "ReviewCreateView", component: ReviewCreateView, props: true,},
  {path: "/review/store/:store_name", name: "StoreReview", component: StoreReviewView, props: true,},

];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeResolve((to, from, next) => {
  if (to.path === "/login" || to.path === "/join" || to.path === "/" || to.path === "/userJoin" || to.path === "/deliveryJoin" || to.path === "/keeperJoin") {
    next();
  } else {
    const dd = async () => {
      try {
        const {data} = await axios.get("/api/user/find");
        await store.dispatch("user/setUser", {"id": data.user_id, "role": data.role});
        if ("anonymousUser" !== data) {
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
