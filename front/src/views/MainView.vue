<template>
  <v-container style="width: 80%">
    <v-divider class="blue-grey" style="border-width: 1px"></v-divider>
    <!-- 로그인시-->
    <div v-if="$store.state.user.userId!='anonymousUser'">
      <v-row style="height:600px;text-align:center" class="pt-1" justify="center">
        <v-col cols="6" align-self="start">
          <v-row>
            <v-col cols="6">
              <img class="" src="/api/photo/altImage/planner4.png" @click="$router.push({path:'/calender'})">
              <p style="font-weight: bold" @click="$router.push({path:'/calender'})">여행플래너 작성하기</p>
              <h2 class="mt-10" style="float:left">맞춤 여행지</h2>
            </v-col>
            <v-col cols="6">
<!--              <v-row></v-row>-->
              <calendar-preview></calendar-preview>
            </v-col>
          </v-row>
          <place-recommend></place-recommend>

        </v-col>
        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="start">
          <v-row>
            <v-col cols="6">
              <img class="" src="/api/photo/altImage/bag4.png" @click="$router.push({path:'/SelectionOrder'})">
              <p style="font-weight: bold" @click="$router.push({path:'/SelectionOrder'})">가방보관/운송 신청하기</p>
            </v-col>

            <v-col cols="6" style="height:300px;overflow: auto">
              <b>내 가방 현황</b>
              <li v-for="(order,i) in myOrders" :key="i"
                  @click="$router.push({path:'/GTrackingView'})"
              >
                <p>{{ order.entrust_time }}->{{ order.withdraw_time }}</p>
                <p>{{ order.keep_start }}->{{ order.keep_end }}</p>
                <p>{{ order.status }}</p>
              </li>
            </v-col>
          </v-row>
          <v-row class="ml-0">
            <h2>추천 여행지</h2>
          </v-row>

          <best-place mode="place"></best-place>
        </v-col>
      </v-row>
      <v-divider class="blue-grey" style="border-width: 1px"></v-divider>

      <v-row style="text-align:center">
        <v-col cols="6" align-self="start">
          <v-row>
            <v-col cols="6">
              <img class="" src="/api/photo/altImage/hotel4.png" @click="$router.push({path:'/place/hotel'})">
              <p style="font-weight: bold" @click="$router.push({path:'/place/hotel'})">호텔 예약하기</p>
            </v-col>
            <v-col cols="6" style="height:300px;overflow: auto"
                   @click="$router.push({path:'/productBookView'})">
              <b>호텔 예약 현황</b>
              <li v-for="(hotel,i) in myHotels" :key="i+h">
                <p>{{ hotel }}</p>
              </li>
            </v-col>
          </v-row>
          <v-row class="ml-0">
            <h2>추천 호텔</h2>
          </v-row>
          <best-place mode="hotel"></best-place>
        </v-col>

        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="start">
          <v-row>
            <v-col cols="6">
              <img class="" src="/api/photo/altImage/guide8.png" @click="$router.push({path:'/GuideProduct'})">
              <p style="font-weight: bold" @click="$router.push({path:'/GuideProduct'})">현지인가이드 신청하기</p>
            </v-col>
            <v-col cols="6" style="height:300px;overflow: auto">
              <b>가이드 예약 현황</b>
              <li v-for="(guide,i) in myGuides" :key="i+g"
                  @click="$router.push({path:'/GuideReserveInfo'})">
                <p>{{ guide.gdate }}{{ guide.title }}</p>
                <p>{{ guide.st_time }}~{{ guide.end_time }}</p>
              </li>
            </v-col>
          </v-row>
          <v-row class="ml-0">
            <h2>추천 가이드</h2>
          </v-row>
          <best-place mode="guide"></best-place>
        </v-col>
      </v-row>
    </div>


    <!-- 비로그인시 -->
    <div v-else>
      <v-row style="height:600px;text-align:center" class="pt-1" justify="center">
        <v-col cols="6" align-self="end" @click="$router.push({path:'/calender'})">
<!--          <img class="" src="/api/photo/altImage/planner4.png">-->
<!--          <p style="font-weight: bold" @click="$router.push({path:'/calender'})">여행플래너 작성하기</p>-->
          <v-row class="ml-0">
            <h2>추천 플래너</h2>
          </v-row>
          <v-row class="mt-5">
            <v-col
              v-for="(share,index) in ShareSets"
              :key="index+s"
              style="padding: 0; margin-bottom: 20px"
              @click="$router.push({name:'shareDetails', params:{id:share.share_id}})"
            >
              <planner-share-card-preview :share-set="share"/>
            </v-col>

          </v-row>

        </v-col>
        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="end">
<!--          <img class="" src="/api/photo/altImage/bag4.png" @click="$router.push({path:'/SelectionOrder'})">-->
<!--          <p style="font-weight: bold" @click="$router.push({path:'/SelectionOrder'})">가방보관/운송 신청하기</p>-->
          <v-row class="ml-0">
            <h2>추천 호텔</h2>
          </v-row>
          <best-place mode="place"></best-place>
        </v-col>
        <v-divider class="blue-grey" style="border-width: 1px"></v-divider>
      </v-row>

      <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
      <v-row style="height:600px;text-align:center" justify="center">
        <v-col cols="6" align-self="end">
<!--          <img class="" src="/api/photo/altImage/hotel4.png"  @click="$router.push({path:'/place/hotel'})">-->
<!--          <p style="font-weight: bold" @click="$router.push({path:'/place/hotel'})">호텔 예약하기</p>-->

          <v-row>
            <h2 style="float:left">추천 호텔</h2>
          </v-row>

          <best-place mode="hotel"></best-place>
        </v-col>

        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="end" >
<!--          <img class="" src="/api/photo/altImage/guide8.png" @click="$router.push({path:'/GuideProduct'})">-->

<!--          <p style="font-weight: bold" @click="$router.push({path:'/GuideProduct'})">현지인가이드 신청하기</p>-->
          <v-row class="ml-0">
            <h2>추천 가이드</h2>
          </v-row>
          <best-place mode="guide"></best-place>
        </v-col>
      </v-row>
    </div>
    {{ $store.state.user.userId }}
    <!--    <v-divider  class="blue-grey" style="border-width: 1px"></v-divider>-->


    <v-row>
      <search-tour></search-tour>
    </v-row>


  </v-container>
</template>

<script>
import axios from "axios";
import PlaceRecommend from "@/components/recommend/PlaceRecommend";
import BestPlace from "@/components/recommend/BestPlace";
import SearchTour from "@/components/SearchTour";
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
        url: '/api/',
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
