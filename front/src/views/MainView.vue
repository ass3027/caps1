<template>
  <v-container style="width: 80%">
    <v-divider  class="blue-grey" style="border-width: 1px"></v-divider>
<!-- 로그인시-->
    <div v-if="$store.state.user.userId!='anonymousUser'">
      <v-row  style="height:600px;text-align:center" class="pt-1" justify="center">
        <v-col cols="6" align-self="start" >
          <v-row>
            <v-col cols="6">
              <img class="" src="/api/photo/altImage/planner4.png" @click="$router.push({path:'/calender'})">
              <p @click="$router.push({path:'/calender'})">여행플래너 작성하기</p>

            </v-col>
            <v-col cols="6">
              <calendar-preview></calendar-preview>
            </v-col>
          </v-row>
          <v-row>
            <place-recommend></place-recommend>
          </v-row>

        </v-col>
        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="start" >
          <v-row>
            <v-col cols="6">
              <img class="" src="/api/photo/altImage/bag4.png" @click="$router.push({path:'/SelectionOrder'})">
              <p @click="$router.push({path:'/SelectionOrder'})">가방보관/운송 신청하기</p>
            </v-col>

            <v-col cols="6"  style="height:300px;overflow: scroll">
                내 가방 현황
                <li v-for="(order,i) in myOrders" :key="i">
                  <p>{{order.entrust_time}}->{{order.withdraw_time}}</p>
                  <p>{{order.keep_start}}->{{order.keep_end}}</p>
                  <p>{{order.status}}</p>
                </li>
            </v-col>
          </v-row>
          <h2>추천 여행지</h2>
          <best-place mode="place"></best-place>
        </v-col>
      </v-row>
      <v-divider class="blue-grey" style="border-width: 1px"></v-divider>

      <v-row>
        <v-col cols="6" align-self="start">
          <v-row>
            <v-col cols="6">
              <img class="" src="/api/photo/altImage/hotel4.png" @click="$router.push({path:'/place/hotel'})">
              <p @click="$router.push({path:'/place/hotel'})">호텔 예약하기</p>
            </v-col>
            <v-col cols="6"  style="height:300px;overflow: scroll">
                호텔 예약 현황
                <li v-for="(hotel,i) in myHotels" :key="i+h">
                  <p>{{hotel}}</p>
                </li>
            </v-col>
          </v-row>
          <h2>추천 호텔</h2>
          <best-place mode="hotel"></best-place>
        </v-col>

        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="start">
          <v-row>
            <v-col cols="6">
              <img class="" src="/api/photo/altImage/guide8.png" @click="$router.push({path:'/GuideProduct'})">
              <p @click="$router.push({path:'/GuideProduct'})">현지인가이드 신청하기</p>
            </v-col>
            <v-col cols="6"  style="height:300px;overflow: scroll">
                가이드 예약 현황
                <li v-for="(guide,i) in myGuides" :key="i+g">
                  <p>{{guide}}</p>
                </li>
            </v-col>
          </v-row>
          <h2>추천 가이드</h2>
          <best-place mode="guide"></best-place>
        </v-col>
      </v-row>

    </div>

<!-- 비로그인시 -->
    <div v-else>
      <v-row  style="height:600px;text-align:center" class="pt-1" justify="center">
        <v-col cols="6" align-self="start" @click="$router.push({path:'/calender'})">
          <img class="" src="/api/photo/altImage/planner4.png">
          <p>여행플래너 작성하기</p>
          <place-recommend></place-recommend>

        </v-col>
        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="start" @click="$router.push({path:'/SelectionOrder'})">
          <img class="" src="/api/photo/altImage/bag4.png">
          <p>가방보관/운송 신청하기</p>

          <h2>best place</h2>
          <best-place mode="place"></best-place>
        </v-col>
        <v-divider class="blue-grey" style="border-width: 1px"></v-divider>

        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="start" @click="$router.push({path:'/place/hotel'})">
          <img class="" src="/api/photo/altImage/hotel4.png">
          <p>호텔 예약하기</p>

          <h2>best hotel</h2>
          <best-place mode="hotel"></best-place>
        </v-col>

        <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
        <v-col cols="6" align-self="start" @click="$router.push({path:'/GuideProduct'})">
          <img class="" src="/api/photo/altImage/guide8.png">

          <h2>best guide</h2>
          <p>현지인가이드 신청하기</p>
          <best-place mode="guide"></best-place>
        </v-col>
      </v-row>
    </div>
    {{ $store.state.user.userId }}
<!--    <v-divider  class="blue-grey" style="border-width: 1px"></v-divider>-->

    <v-row justify="center">
      <v-col cols="2">
      </v-col>
      <v-col cols="2">
      </v-col>
      <v-col cols="2">
      </v-col>
      <v-col cols="2">
      </v-col>

    </v-row>

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

export default {
  name: 'MainView',
  components: {
    BestPlace,
    PlaceRecommend,
    SearchTour,
    CalendarPreview
  },
  data() {
    return {
      myOrders:'',
      myHotels:'',
      myGuides:''
    }
  },
  mounted() {
    if(this.$store.state.user.userId!=''){
      axios.get('/api/orders/myOrders',{params:{user_id:this.$store.state.user.userId}})
      .then((res)=>{
        this.myOrders=res.data
      })
      axios({
        method:'get',
        url:'/api/gTime',
        params:{
          'id':this.$store.state.user.userId
        }
      })
        .then((res)=>{
          this.myGuides = res.data;
          console.log(res.data)
        })
      axios({
        method:'GET',
        url:'/api/',
        params:{
          'id':this.$store.state.user.userId
        }
      })
        .then((res)=>{
          this.myHotels = res.data;
          console.log(res.data)
        })

    }
  },
  methods: {
    test(){
      this.$router.push({path:"/calender"})
    }
  },
}
</script>
<style scoped>
img{
  width:200px;
  height: 200px;
  margin-top:15px;
}
</style>
