<template>
  <v-container style="width: 80%">
    <v-divider
      class="blue-grey"
      style="border-width: 1px"
    />
    <!-- 로그인시-->
    <div v-if="$store.state.user.userId!='anonymousUser'">
      <v-row
        style="height:400px;"
        class="mb-5"
        justify="center"
      >
        <v-col
          cols="3"
          align-self="start"
          class="mt-5"
          style=""
        >
          <v-card
            class="pa-3"
            elevation="10"
            style="width: 330px;height:380px;overflow: auto"
          >
            <b>일정 현황</b>
            <calendar-preview/>
          </v-card>
        </v-col>
        <v-col
          cols="auto"
          align-self="start"
          style=""
          class="mt-5 ml-5"
        >
          <v-card
            class="pa-3"
            elevation="10"
            style="width: 330px;height:380px;overflow: auto"
          >
            <b>내 가방 현황</b>
            <li
              v-for="(order,i) in myOrders"
              :key="i+o"
              @click="$router.push({path:'/GTrackingView'})"
            >
              <p>{{ order.entrust_time }}->{{ order.withdraw_time }}</p>
              <p>{{ order.keep_start }}->{{ order.keep_end }}</p>
              <p>{{ order.status }}</p>
            </li>
          </v-card>
        </v-col>
        <v-col
          cols="auto"
          align-self="start"
          style=""
          class="mt-5 ml-5"
        >
          <v-card
            class="pa-3"
            elevation="10"
            style="width: 330px;height:380px;overflow: auto"
          >
            <b>호텔 예약 현황</b>
            <li
              v-for="(hotel,i) in myHotels"
              :key="i+h"
              @click="$router.push({path:'/productBookView'})"
            >
              <p>{{ hotel.st_date.substring(0,10) }}~{{ hotel.end_date.substring(0,10) }}</p>
              <p>{{ hotel.title }}</p>
            </li>
          </v-card>
        </v-col>
        <v-col
          cols="auto"
          align-self="start"
          style=""
          class="mt-5 ml-5"
        >
          <v-card
            class="pa-3"
            elevation="10"
            style="width: 330px;height:380px;overflow: auto"
          >
            <b>가이드 예약 현황</b>
            <li
              v-for="(guide,i) in myGuides"
              :key="i+g"
              @click="$router.push({path:'/GuideReserveInfo'})"
            >
              <p>{{ guide.gdate }}{{ guide.title }}</p>
              <p>{{ guide.st_time }}~{{ guide.end_time }}</p>
            </li>
          </v-card>
        </v-col>
      </v-row>
    </div>

    <!--            <v-col cols="6" style="height:300px;overflow: auto"-->
    <!--                   @click="$router.push({path:'/productBookView'})">-->

    <div v-if="$store.state.user.userId=='anonymousUser'">
      <v-row>
        <search-tour/>
      </v-row>
      <v-row
        justify="center"
        style="text-align: center"
      >
        <v-col cols="2">
          <img
            class=""
            src="/api/photo/altImage/planner4.png"
            @click="$router.push({path:'/calender'})"
          >
        </v-col>
        <v-col cols="2">
          <img
            class=""
            src="/api/photo/altImage/bag4.png"
            @click="$router.push({path:'/SelectionOrder'})"
          >
        </v-col>
        <v-col cols="2">
          <img
            class=""
            src="/api/photo/altImage/hotel4.png"
            @click="$router.push({path:'/place/hotel'})"
          >
        </v-col>
        <v-col cols="2">
          <img
            class=""
            src="/api/photo/altImage/guide8.png"
            @click="$router.push({path:'/GuideProduct'})"
          >
        </v-col>
      </v-row>
      <v-row
        justify="center"
        style="text-align: center"
      >
        <v-col cols="2">
          <p
            style="font-weight: bold"
            @click="$router.push({path:'/calender'})"
          >
            여행플래너 작성하기
          </p>
        </v-col>
        <v-col cols="2">
          <p
            style="font-weight: bold"
            @click="$router.push({path:'/SelectionOrder'})"
          >
            가방보관/운송 신청하기
          </p>
        </v-col>
        <v-col cols="2">
          <p
            style="font-weight: bold"
            @click="$router.push({path:'/place/hotel'})"
          >
            호텔 예약하기
          </p>
        </v-col>
        <v-col cols="2">
          <p
            style="font-weight: bold"
            @click="$router.push({path:'/GuideProduct'})"
          >
            현지인가이드 신청하기
          </p>
        </v-col>
      </v-row>
    </div>



    <div v-if="$store.state.user.userId!='anonymousUser'">
      <v-row class="mt-10">
        <h2>맞춤 여행지</h2>
      </v-row>
      <place-recommend/>
    </div>


    <v-row
      justify="start"
      class="mt-5"
    >
      <h2>추천 여행지</h2>
    </v-row>
    <v-row class="mb-5">
      <best-place mode="place"/>
    </v-row>
    <v-row>
      <h2>추천 호텔</h2>
    </v-row>
    <v-row class="mb-5">
      <best-place mode="hotel"/>
    </v-row>


    <v-row>
      <h2>추천 가이드</h2>
    </v-row>
    <v-row class="mb-5">
      <best-place mode="guide"/>
    </v-row>

    <v-row class="ml-0">
      <h2>추천 플래너</h2>
    </v-row>
    <v-row class="mt-5">
      <v-col
        v-for="(share,index) in ShareSets"
        :key="index+s"
        style="padding: 0; margin-bottom: 20px"
        cols="2"
        @click="$router.push({name:'shareDetails', params:{id:share.share_id}})"
      >
        <planner-share-card-preview :share-set="share"/>
      </v-col>
    </v-row>

    <div v-if="$store.state.user.userId!='anonymousUser'">
      <v-row>
        <search-tour />
      </v-row>


      <v-row
        justify="center"
        style="text-align: center"
      >
        <v-col cols="2">
          <img
            class=""
            src="/api/photo/altImage/planner4.png"
            @click="$router.push({path:'/calender'})"
          >
        </v-col>
        <v-col cols="2">
          <img
            class=""
            src="/api/photo/altImage/bag4.png"
            @click="$router.push({path:'/SelectionOrder'})"
          >
        </v-col>
        <v-col cols="2">
          <img
            class=""
            src="/api/photo/altImage/hotel4.png"
            @click="$router.push({path:'/place/hotel'})"
          >
        </v-col>
        <v-col cols="2">
          <img
            class=""
            src="/api/photo/altImage/guide8.png"
            @click="$router.push({path:'/GuideProduct'})"
          >
        </v-col>
      </v-row>
      <v-row
        justify="center"
        style="text-align: center"
      >
        <v-col cols="2">
          <p
            style="font-weight: bold"
            @click="$router.push({path:'/calender'})"
          >
            여행플래너 작성하기
          </p>
        </v-col>
        <v-col cols="2">
          <p
            style="font-weight: bold"
            @click="$router.push({path:'/SelectionOrder'})"
          >
            가방보관/운송 신청하기
          </p>
        </v-col>
        <v-col cols="2">
          <p
            style="font-weight: bold"
            @click="$router.push({path:'/place/hotel'})"
          >
            호텔 예약하기
          </p>
        </v-col>
        <v-col cols="2">
          <p
            style="font-weight: bold"
            @click="$router.push({path:'/GuideProduct'})"
          >
            현지인가이드 신청하기
          </p>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import PlaceRecommend from "@/components/recommend/PlaceRecommend";
import BestPlace from "@/components/recommend/BestPlace";
import SearchTour from "@/components/search/SearchTour";
import CalendarPreview from "@/components/preview/CalendarPreview";
import PlannerShareCardPreview from "@/components/recommend/PlannerShareCardPreview";

export default {
  name: 'MainView',
  components: {
    BestPlace,
    PlaceRecommend,
    SearchTour,
    CalendarPreview,
    PlannerShareCardPreview
  },
  data() {
    return {
      myOrders: '',
      myHotels: '',
      myGuides: '',
      ShareSets: [],
      picker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),

    }
  },
  mounted() {
    if (this.$store.state.user.userId != '') {
      axios.get('/api/orders/myOrders', {params: {user_id: this.$store.state.user.userId}})
        .then((res) => {
          this.myOrders = res.data
        })
      axios({
        method: 'get',
        url: '/api/gTime',
        params: {
          'id': this.$store.state.user.userId
        }
      })
        .then((res) => {
          this.myGuides = res.data;
          console.log(res.data)
        })
      axios({
        method: 'GET',
        url: '/api/productBook',
        params: {
          'id': this.$store.state.user.userId
        }
      })
        .then((res) => {
          this.myHotels = res.data;
          console.log(res.data)
        })
    }

    axios.get('/api/bestShare')
      .then((res) => {
        //날짜변환
        let now = new Date()
        let today = new Date(now.toLocaleDateString());
        res.data.forEach(i => {
          var thisDate = new Date(i.share_created)
          if (today > thisDate) {
            i.share_created = i.share_created.substring(0, 10)
          } else {
            i.share_created = thisDate.toString().substring(16, 21)
          }
        })
        this.ShareSets = res.data;
        console.log(this.ShareSets)
      })
<<<<<<< Updated upstream

=======
  },
  destroyed() {
    window.scrollTo(0, 0)
>>>>>>> Stashed changes
  },
  methods: {
    test() {
      this.$router.push({path: "/calender"})
    }
  },
}
</script>
<style scoped>
img {
  width: 100px;
  height: 100px;
  margin-top: 15px;
}
</style>
