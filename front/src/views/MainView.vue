<template>
  <v-container>
    <v-divider></v-divider>
    <v-row style="height:500px;text-align: center" class="">
      <v-col cols="3" align-self="center" @click="$router.push({path:'/calender'})">
        <img class="" src="/api/photo/altImage/planner2.png">
        <p>여행플래너 작성하기</p>
      </v-col>
      <v-divider vertical></v-divider>
      <v-col cols="3" align-self="center" @click="$router.push({path:'/SelectionOrder'})">
        <img class="" src="/api/photo/altImage/bag1.png">
        <p>가방보관/운송 신청하기</p>

      </v-col>
      <v-divider vertical></v-divider>
      <v-col cols="3" align-self="center" @click="$router.push({path:'/place/hotel'})">
        <img class="" src="/api/photo/altImage/hotel3.png">
        <p>호텔 예약하기</p>

      </v-col>
      <v-divider vertical></v-divider>
      <v-col cols="3" align-self="center" @click="$router.push({path:'/GuideProduct'})">
        <img class="" src="/api/photo/altImage/guide7.png">
        <p>현지인가이드 신청하기</p>

      </v-col>
    </v-row>
    <v-row>
      <v-col cols="3">
        <v-card class="pa-5 ma-5">

          <place-recommend></place-recommend>
        </v-card>

      </v-col>
      <v-col cols="3">
        <v-card class="pa-5 ma-5">
          <best-place mode="place"></best-place>
        </v-card>
      </v-col>
      <v-col cols="3">
        <v-card class="pa-5 ma-5">
          <best-place mode="hotel"></best-place>
        </v-card>
      </v-col>
      <v-col cols="3">
        <v-card class="pa-5 ma-5">
          <best-place mode="guide"></best-place>
        </v-card>
      </v-col>


    </v-row>

  </v-container>
</template>

<script>
// import axios from "axios";
import PlaceRecommend from "@/components/recommend/PlaceRecommend";
import BestPlace from "@/components/recommend/BestPlace";
export default {
  name: 'MainView',
  components: {
    BestPlace,
    PlaceRecommend
  },
  data() {
    return {}
  },
  mounted() {
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
  width:300px;
  height: 300px;

}
</style>
